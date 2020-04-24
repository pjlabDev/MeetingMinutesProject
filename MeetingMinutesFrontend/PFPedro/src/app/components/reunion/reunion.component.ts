import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReunionService } from '../../services/reunion.service';
import { TemasService } from '../../services/temas.service';
import { UsuarioService } from '../../services/usuario.service';
import { SeriereunionService } from '../../services/seriereunion.service';
import { SerieReunion } from '../../clases/serie-reunion';
import { Usuario } from '../../clases/usuario';
import { Reunion } from '../../clases/reunion';
import { Temas } from '../../clases/temas';
import { EmailService } from '../../services/email.service';
import { DatePipe } from '@angular/common';
import Swal from 'sweetalert2';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Archivos } from '../../clases/archivos';
import { ArchivoService } from '../../services/archivo.service';

@Component({
  selector: 'app-reunion',
  templateUrl: './reunion.component.html',
  styleUrls: ['./reunion.component.scss'],
  providers: [DatePipe]
})
export class ReunionComponent implements OnInit {

  reunion: Reunion = new Reunion();
  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  codsreunion: number;
  codreunion: number;
  temas: Temas[];
  fechaReunion: string;
  codigos: number[] = [];
  adjuntar = false;
  archivo: Archivos = new Archivos();

  formArchivo = new FormGroup({
    archivo: new FormControl('', [Validators.required])
  });

  selectedFiles: FileList;
  currentFile: File;
  archivos: Archivos[];
  existeArchivo = false;

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, public rs: ReunionService, public router: Router, public es: EmailService,
              public datepipe: DatePipe, public as: ArchivoService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.codreunion = parseInt(response.get('idd'), 10);
      this.getSerieReunionById(this.codsreunion);
      this.rs.getReunionByCodReunion(this.codreunion).subscribe(data => {
        this.reunion = data;
      });
      this.getTemas(this.codsreunion);
      this.us.getUsuariosByCodReunion(this.codreunion).subscribe(data => {
        this.usuarios = data;
      });
      this.getArchivosByCodreunion(this.codreunion);
    });
  }

  getSerieReunionById(id: number) {
    this.sr.getSerieReunionByCodReunion(id).subscribe(data => {
      this.serieReunion = data;
    }, error => {
      console.log('Error al recibir la serieReunion: ', error);
    });
  }

  getTemas(id: number) {
    this.ts.getTemasBySerieReunion(id).subscribe(data => {
      this.temas = data;
    });
  }

  enviarAgenda() {
    this.fechaReunion = this.datepipe.transform(this.reunion.fecha, 'dd-MM-yyyy');

    this.usuarios.forEach(response => {
      this.codigos.push(response.codUsu);
    });

    this.es.enviarAgenda(this.codigos, this.fechaReunion, this.temas).subscribe(dat => {
        Swal.fire({
          icon: 'success',
          title: 'Agenda enviada a los participantes de esta reunión.',
          showConfirmButton: false,
          timer: 1500
        });
      }, error => {
        console.log('Error al enviar la agenda', error);
      });
  }

  submittedTrue() {
    this.adjuntar = true;
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  enviarArchivo() {
    this.currentFile = this.selectedFiles.item(0);
    this.archivo.nombre = this.currentFile.name;
    this.as.adjuntarArchivo(this.archivo.nombre, this.currentFile, this.codreunion).subscribe(res => {
      Swal.fire({
        icon: 'success',
        title: 'Archivo guardado en base de datos.',
        showConfirmButton: false,
        timer: 1500
      });
      this.formArchivo.reset();
      this.adjuntar = false;
      this.getArchivosByCodreunion(this.codreunion);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al adjuntar archivo',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.formArchivo.reset();
      console.log('error de archivo: ', error);
    });
  }

  getArchivosByCodreunion(id: number) {
    this.as.getArchivosByCodReunion(id).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.existeArchivo = true;
        this.archivos = data;
      } else {
        this.existeArchivo = false;
      }
    }, error => {
      console.log('Error al listar archivos: ', error);
    });
  }

  ejecutarArchivo(id: number, nombreArchivo: string) {
    this.as.ejecutarArchivo(id, nombreArchivo);
  }

  borrarArchivo(codarchivo: number) {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'El archivo se eliminará si aceptas.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
      if (result.value) {
        this.as.borrarArchivo(codarchivo).subscribe(data => {
          Swal.fire(
            'Eliminado!',
            'El archivo ha sido eliminado.',
            'success'
          );
          this.getArchivosByCodreunion(this.codreunion);
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Lo sentimos, ha ocurrido un problema al borrar el archivo',
            text: 'Inténtelo de nuevo o mas tarde.',
            timer: 1500
          });
          console.log('error de archivo: ', error);
        });
      }
    });
  }

}
