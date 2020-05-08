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
    conclusion: new FormControl('', [Validators.required, Validators.maxLength(500)])
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
    this.acta.fecha = form.value.fecha;
    this.codigos = form.value.asistentes;

    console.log(form.value.conclusion);
    console.log(this.temas);
    console.log(this.tareas);

    /* this.as.generarActa(this.acta, this.codreunion, this.codigos).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Acta generada con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.nuevaActaForm.reset();
      this.router.navigate(['/', 'reunion', this.codsreunion, this.codreunion]);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema a la hora de crear la reunión.',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.nuevaActaForm.reset();
      console.log('Error al generar acta.', error);
    }); */
  }

  enviarActa(form: NgForm) {

    if (this.temas !== undefined) {
      console.log(this.temas);
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

    console.log('Codigos de los temas: ', this.codtemas);
    console.log('Codigos de las tareas: ', this.codtareas);
    console.log('Fecha Acta: ', form.value.fecha);
    console.log('Codigos de los asistentes: ', this.codigos);

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
