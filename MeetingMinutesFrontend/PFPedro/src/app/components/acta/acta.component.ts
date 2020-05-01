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

@Component({
  selector: 'app-acta',
  templateUrl: './acta.component.html',
  styleUrls: ['./acta.component.scss']
})
export class ActaComponent implements OnInit {

  reunion: Reunion = new Reunion();
  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  codsreunion: number;
  codreunion: number;
  codigos: number[] = [];
  acta: Acta = new Acta();

  tarea: Tareas = new Tareas();
  tareas: Tareas[];
  tareasAntiguasNoCerradas: Tareas[];
  existeTarea = false;
  existeTareaAntigua = false;

  tema: Temas = new Temas();
  temas: Temas[];
  temasAntiguosNoCerrados: Temas[];
  existeTema = false;
  existeTemaAntiguo = false;

  nuevaActaForm = new FormGroup({
    fecha: new FormControl('', [Validators.required]),
    asistentes: new FormControl('', [Validators.required]),
    conclusion: new FormControl('', [Validators.required, Validators.maxLength(500)])
  });

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, public rs: ReunionService, public router: Router, public as: ActaService,
              public tarS: TareasService) { }

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
      this.getTareas(this.codreunion);
      this.getTareasAntiguasNoCerradas(this.codreunion);
      this.getTemas(this.codreunion);
      this.getTemasAntiguosNoCerrados(this.codreunion);
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
    this.ts.getTemasByCodReunionAndNoCerrado(id).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.existeTema = true;
        this.temas = data;
      } else {
        this.existeTema = false;
      }
    });
  }

  getTemasAntiguosNoCerrados(id: number) {
    this.ts.getTemasByCodReunionAntiguaAndNoCerrado(id).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.existeTemaAntiguo = true;
        this.temasAntiguosNoCerrados = data;
      } else {
        this.existeTemaAntiguo = false;
      }
    });
  }

  getTareas(id: number) {
    this.tarS.getTareasByCodReunionAndNoCerrado(id).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.existeTarea = true;
        this.tareas = data;
      } else {
        this.existeTarea = false;
      }
    });
  }

  getTareasAntiguasNoCerradas(id: number) {
    this.tarS.getTareasByCodReunionAntiguaAndNoCerrado(id).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.existeTareaAntigua = true;
        this.tareasAntiguasNoCerradas = data;
      } else {
        this.existeTareaAntigua = false;
      }
    });
  }

  generarActa(form: NgForm) {
    this.acta.fecha = form.value.fecha;
    this.codigos = form.value.asistentes;

    console.log(form.value.conclusion);
    console.log(this.temas);
    console.log(this.temasAntiguosNoCerrados);
    console.log(this.tareas);
    console.log(this.tareasAntiguasNoCerradas);

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

  cerrarTemas(codtema: number) {

    this.tema = new Temas();
    this.tema.codTema = codtema;

    Swal.fire({
      title: '¿Estás seguro?',
      text: 'El tema se cerrará si aceptas.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, cerrar!'
    }).then((result) => {
      if (result.value) {
        this.ts.cerrarTemas(this.tema).subscribe(data => {
          Swal.fire(
            'Cerrado!',
            'El tema ha sido cerrado.',
            'success'
          );
          this.getTemas(this.codreunion);
          this.getTemasAntiguosNoCerrados(this.codreunion);
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Lo sentimos, ha ocurrido un problema al cerrar el tema',
            text: 'Inténtelo de nuevo o mas tarde.',
            timer: 1500
          });
          console.log('error de tema: ', error);
        });
      }
    });
  }

  cerrarTareas(codtarea: number) {

    this.tarea = new Tareas();
    this.tarea.codTarea = codtarea;

    Swal.fire({
      title: '¿Estás seguro?',
      text: 'La tarea se cerrará si aceptas.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, cerrar!'
    }).then((result) => {
      if (result.value) {
        this.tarS.cerrarTareas(this.tarea).subscribe(data => {
          Swal.fire(
            'Cerrada!',
            'La tarea ha sido cerrada.',
            'success'
          );
          this.getTareas(this.codreunion);
          this.getTareasAntiguasNoCerradas(this.codreunion);
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Lo sentimos, ha ocurrido un problema al cerrar la tarea',
            text: 'Inténtelo de nuevo o mas tarde.',
            timer: 1500
          });
          console.log('error de tarea: ', error);
        });
      }
    });
  }

  get conclusion() {
    return this.nuevaActaForm.get('conclusion');
  }

}
