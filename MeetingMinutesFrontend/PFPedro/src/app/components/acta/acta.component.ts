import { Component, OnInit } from '@angular/core';
import { Reunion } from '../../clases/reunion';
import { TemasService } from '../../services/temas.service';
import { ReunionService } from '../../services/reunion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SeriereunionService } from '../../services/seriereunion.service';
import { UsuarioService } from '../../services/usuario.service';
import { SerieReunion } from '../../clases/serie-reunion';
import { Usuario } from '../../clases/usuario';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Acta } from '../../clases/acta';
import { ActaService } from '../../services/acta.service';
import Swal from 'sweetalert2';
import { TareasService } from '../../services/tareas.service';
import { Tareas } from '../../clases/tareas';
import { Temas } from '../../clases/temas';
import { EmailService } from '../../services/email.service';
import { DatePipe } from '@angular/common';
import * as jsPDF from 'jspdf';
import * as html2canvas from 'html2canvas';

@Component({
  selector: 'app-acta',
  templateUrl: './acta.component.html',
  styleUrls: ['./acta.component.scss'],
  providers: [DatePipe]
})
export class ActaComponent implements OnInit {

  reunion: Reunion = new Reunion();
  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  codsreunion: number;
  codreunion: number;
  codigos: number[] = [];
  acta: Acta = new Acta();
  fechaActa: string;
  asistentes: Usuario[] = [];

  tarea: Tareas = new Tareas();
  tareas: Tareas[];
  codtareas: number[] = [];
  existeTarea = false;

  tema: Temas = new Temas();
  temas: Temas[];
  codtemas: number[] = [];
  existeTema = false;

  nuevaActaForm = new FormGroup({
    fecha: new FormControl('', [Validators.required]),
    asistentes: new FormControl('', [Validators.required]),
    conclusion: new FormControl('', [Validators.required, Validators.maxLength(1500)])
  });

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, public rs: ReunionService, public router: Router, public as: ActaService,
              public tarS: TareasService, public es: EmailService, public datepipe: DatePipe) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.codreunion = parseInt(response.get('idd'), 10);
      this.getSerieReunionById(this.codsreunion);
      this.rs.getReunionByCodReunion(this.codreunion).subscribe(data => {
        this.reunion = data;
      });
      this.us.getUsuariosByCodReunion(this.codreunion).subscribe(data => {
        this.usuarios = data;
      });
      this.getTemas(this.codreunion);
      this.getTareas(this.codreunion);
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
      if (data !== null && data.length !== 0) {
        this.existeTema = true;
        this.temas = data;
      } else {
        this.existeTema = false;
      }
    });
  }

  getTareas(id: number) {
    this.tarS.getTareasByCodReunion(id).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.existeTarea = true;
        this.tareas = data;
      } else {
        this.existeTarea = false;
      }
    });
  }

  generarActa(form: NgForm) {

    this.codigos = form.value.asistentes;

    let users = '';

    if (this.codigos.length > 0) {
      this.us.getUsuariosByCodUsu(this.codigos).subscribe(data => {
        this.asistentes = data;
        if (this.asistentes.length > 0) {
          this.asistentes.forEach(res => {
            users += res.nombre + ' - ' + res.rol + '\n';
          });
        }
      });
    }

    const titulo1 = document.getElementById('title').textContent;
    const titulo2 = document.getElementById('title2').textContent;

    const doc = new jsPDF();
    /* doc.text('Sevilla, Cantillana' + '\n' + 'Email: pedroperlab@gmail.com' + '\n' + 'Contacto: 667091224' + '\n'
    + 'https://www.github.com/PedrassoxD', 10, 20); */
    doc.text(titulo1, 80, 20);
    doc.text(titulo2, 50, 30);
    doc.text(`FECHA: ${form.value.fecha}`, 10, 50);
    doc.text('ASISTENTES: ' + '\n' + this.codigos, 10, 70);
    /* doc.text('CONCLUSIÓN: ' + '\n' + form.value.conclusion, 140, 50); */
    doc.fromHTML(document.getElementById('temasytareas'), 10, 90);
    /* const contador = doc.internal.getNumberOfPages();
    for (let i = 0; i < contador; i++) {
      const currentPage = doc.internal.getCurrentPageInfo().pageNumber;
      doc.setPage(i);
      if (currentPage < contador) {
        doc.text(100, 285, 'Página ' + currentPage + ' de ' + contador);
      } else {
        doc.text(10, 250, 'Conclusión: ' + '\n' + form.value.conclusion);
      }
    } */
    /* doc.text('Conclusión.- ' + '\n' + form.value.conclusion, 10, 150); */
    doc.save('test.pdf');
  }

  enviarActa(form: NgForm) {

    if (this.temas !== undefined) {
      this.temas.forEach(res => {
        this.codtemas.push(res.codTema);
      });
    } else {
      this.codtemas.push(-1);
    }

    if (this.tareas !== undefined) {
      this.tareas.forEach(res => {
        this.codtareas.push(res.codTarea);
      });
    } else {
      this.codtareas.push(-1);
    }


    this.codigos = form.value.asistentes;
    this.fechaActa = this.datepipe.transform(form.value.fecha, 'dd-MM-yyyy');

    Swal.fire({
      icon: 'success',
      title: 'Acta enviada a todos los asistentes.',
      showConfirmButton: false,
      timer: 1500
    });

    this.es.enviarActa(this.codigos, this.fechaActa, this.codtemas, this.codtareas, form.value.conclusion).subscribe(data => {
      this.codigos = [];
      this.codtemas = [];
      this.codtareas = [];
    }, error => console.log('Error al enviar acta x email: ', error));

  }

  get conclusion() {
    return this.nuevaActaForm.get('conclusion');
  }

}
