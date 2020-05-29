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
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

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
  temasAntiguos: Temas[];
  existeTemaAntiguo = false;
  fechaReunion: string;
  codigos: number[] = [];
  codigosTemasAntiguos: number[] = [];
  adjuntar = false;
  archivo: Archivos = new Archivos();

  formArchivo = new FormGroup({
    archivo: new FormControl('', [Validators.required])
  });

  archivosSeleccionados: FileList;
  archivoEnCruso: File;
  archivos: Archivos[];
  existeArchivo = false;

  formReunion = new FormGroup({
    fecha: new FormControl('', [Validators.required]),
    newparticipantes: new FormControl('')
  });
  modificar = false;
  nomodificar = true;
  usuariosNotInReunion: Usuario[];

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, public rs: ReunionService, public router: Router, public es: EmailService,
              public datepipe: DatePipe, public as: ArchivoService, private modalService: NgbModal) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.codreunion = parseInt(response.get('idd'), 10);
      this.getSerieReunionById(this.codsreunion);
      this.getReunion(this.codreunion);
      this.getTemas(this.codreunion);
      this.getTemasAntiguosNoCerrados(this.codreunion, this.codsreunion);
      this.getUsuariosByCodReunion(this.codreunion);
      this.getArchivosByCodreunion(this.codreunion);
      this.getUsuariosNotInReunion(this.codreunion, this.codsreunion);
    });
  }

  getReunion(id: number) {
    this.rs.getReunionByCodReunion(id).subscribe(data => {
      this.reunion = data;
    });
  }

  getUsuariosByCodReunion(id: number) {
    this.us.getUsuariosByCodReunion(id).subscribe(data => {
      this.usuarios = data;
    });
  }

  getSerieReunionById(id: number) {
    this.sr.getSerieReunionByCodSReunion(id).subscribe(data => {
      this.serieReunion = data;
    }, error => {
      console.log('Error al recibir la serieReunion: ', error);
    });
  }

  getTemas(id: number) {
    this.ts.getTemasByCodReunion(id).subscribe(data => {
      this.temas = data;
    });
  }

  getTemasAntiguosNoCerrados(id: number, id2: number) {
    this.ts.getTemasByCodReunionAntiguaAndNoCerrado(id, id2).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.temasAntiguos = data;
        if (this.temasAntiguos.length > 0) {
          this.ts.saveTemaAntiguo(this.temasAntiguos, this.codreunion).subscribe(res => {
            this.getTemas(this.codreunion);
          }, error => console.log('Error al guardar tema antiguo: ', error));
        }
      }
    });
  }

  getUsuariosNotInReunion(id: number, idd: number) {
    this.us.getUsuariosNotInReunion(id, idd).subscribe(data => {
      this.usuariosNotInReunion = data;
    });
  }

  enviarAgenda() {
    this.fechaReunion = this.datepipe.transform(this.reunion.fecha, 'dd-MM-yyyy');

    this.usuarios.forEach(response => {
      this.codigos.push(response.codUsu);
    });

    Swal.fire({
      icon: 'success',
      title: 'Agenda enviada a los participantes de esta reunión.',
      showConfirmButton: false,
      timer: 1500
    });

    this.es.enviarAgenda(this.codigos, this.fechaReunion, this.temas).subscribe(dat => {
        this.codigos = [];
      }, error => {
        console.log('Error al enviar la agenda', error);
      });
  }

  submittedTrue() {
    this.adjuntar = true;
  }

  selectFile(event) {
    this.archivosSeleccionados = event.target.files;
  }

  guardarArchivo() {
    this.archivoEnCruso = this.archivosSeleccionados.item(0);
    this.archivo.nombre = this.archivoEnCruso.name;
    this.as.adjuntarArchivo(this.archivo.nombre, this.archivoEnCruso, this.codreunion).subscribe(res => {
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

  verReunion(modal) {
    this.fecha.disable();
    this.fecha.setValue(this.reunion.fecha);
    this.modalService.open(modal);
  }

  siReunion() {
    this.nomodificar = false;
    this.modificar = true;
    this.fecha.enable();
  }

  modificarReunion(form: NgForm, modal) {

    this.reunion.fecha = form.value.fecha;

    let cods = [-1];

    if (form.value.newparticipantes !== '') {
      cods = form.value.newparticipantes;
    }

    this.rs.modificarReunion(this.reunion, cods).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Reunión modificada con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.getReunion(this.codreunion);
      this.getUsuariosByCodReunion(this.codreunion);
      this.cerrarModalReunion(modal);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al modificar la reunión',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      console.log(error);
      this.cerrarModalReunion(modal);
    });

  }

  cerrarModalReunion(modal) {
    this.nomodificar = true;
    this.modificar = false;
    this.modalService.dismissAll(modal);
    this.formReunion.reset();
  }

  eliminarParticipante(codusu: number) {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'El participante se eliminará si aceptas.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
      if (result.value) {
        this.rs.eliminarParticipante(this.reunion, codusu).subscribe(data => {
          Swal.fire(
            'Eliminado!',
            'Participante eliminado.',
            'success'
          );
          this.getReunion(this.codreunion);
          this.getUsuariosByCodReunion(this.codreunion);
          this.getUsuariosNotInReunion(this.codreunion, this.codsreunion);
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Lo sentimos, ha ocurrido un problema al borrar al participante',
            text: 'Inténtelo de nuevo o mas tarde.',
            timer: 1500
          });
          console.log('error eliminar participante: ', error);
        });
      }
    });
  }

  get fecha() {
    return this.formReunion.get('fecha');
  }

}
