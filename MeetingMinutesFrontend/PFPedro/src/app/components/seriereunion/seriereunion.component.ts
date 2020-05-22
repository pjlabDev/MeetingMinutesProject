import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SeriereunionService } from '../../services/seriereunion.service';
import { SerieReunion } from '../../clases/serie-reunion';
import { Usuario } from '../../clases/usuario';
import { UsuarioService } from '../../services/usuario.service';
import { ReunionService } from '../../services/reunion.service';
import { Reunion } from '../../clases/reunion';
import { TemasService } from '../../services/temas.service';
import { Temas } from '../../clases/temas';
import { TareasService } from '../../services/tareas.service';
import { Tareas } from '../../clases/tareas';
import { FormGroup, Validators, FormControl, NgForm } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-seriereunion',
  templateUrl: './seriereunion.component.html',
  styleUrls: ['./seriereunion.component.scss']
})
export class SeriereunionComponent implements OnInit {

  filtroBusqueda;
  codsreunion: number;
  usuarios: Usuario[];
  reunion: Reunion[];
  temas: Temas[];
  tareas: Tareas[];
  serieReunion: SerieReunion = new SerieReunion();
  isReunion = false;
  isTema = false;
  isTareas = false;
  usuario: Usuario = new Usuario();

  infoTemaForm = new FormGroup({
    tituloTema: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    etiquetaTema: new FormControl('', [Validators.required]),
    infoTema: new FormControl('', [Validators.required, Validators.maxLength(255)]),
    decisionTema: new FormControl('', [Validators.required, Validators.maxLength(255)])
  });
  tema: Temas = new Temas();

  infoTareaForm = new FormGroup({
    tituloTarea: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    descripTarea: new FormControl('', [Validators.required, Validators.maxLength(255)]),
    responsablesTarea: new FormControl('')
  });
  tarea: Tareas = new Tareas();
  usuariosnotintarea: Usuario[];

  modificar = false;
  nomodificar = true;

  constructor(public route: ActivatedRoute, private sr: SeriereunionService, public us: UsuarioService, public rs: ReunionService,
              public ts: TemasService, public tarS: TareasService, private modalService: NgbModal) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.sr.getSerieReunionByCodSReunion(this.codsreunion).subscribe(data => {
          this.serieReunion = data;
      });
      this.us.getUsuariosInSerieReunion(this.codsreunion).subscribe(data => {
        this.usuarios = data;
      });
      this.getAllTemasByCodSReunion(this.codsreunion);
      this.getAllTareasByCodSReunion(this.codsreunion);
    });
    this.getReuniones();
  }

  getAllTemasByCodSReunion(id: number) {
    this.ts.getAllTemasByCodSReunion(id).subscribe(data => {
      this.temas = data;
    });
  }

  getAllTareasByCodSReunion(id: number) {
    this.tarS.getAllTareasByCodSReunion(id).subscribe(data => {
      this.tareas = data;
    });
  }

  verReuniones() {
    this.isTema = false;
    this.isTareas = false;
    this.isReunion = true;
  }

  verTemas() {
    this.isReunion = false;
    this.isTareas = false;
    this.isTema = true;
  }

  verTareas() {
    this.isReunion = false;
    this.isTema = false;
    this.isTareas = true;
  }

  getReuniones() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.rs.getReuniones(this.usuario.codUsu, this.codsreunion).subscribe(data => {
      this.reunion = data;
      if (this.reunion.length > 0) {
        this.isReunion = true;
      } else {
        this.isReunion = false;
      }
    }, error => {
      console.log('Error al traer reunion', error);
    });
  }


  /** MODAL PARA LOS TEMAS */

  verTema(id: number, modal) {
    this.ts.getTemaByCodTema(id).subscribe(data => {
      this.tema = data;

      this.tituloTema.disable();
      this.etiquetaTema.disable();
      this.infoTema.disable();
      this.decisionTema.disable();

      this.tituloTema.setValue(this.tema.titulo);
      this.etiquetaTema.setValue(this.tema.etiqueta);
      this.infoTema.setValue(this.tema.info);
      this.decisionTema.setValue(this.tema.decision);
    });
    this.modalService.open(modal);
  }

  cerrarModalTema(modal) {
    this.infoTemaForm.reset();
    this.modalService.dismissAll(modal);
    this.nomodificar = true;
    this.modificar = false;
  }

  siTema() {
    this.nomodificar = false;
    this.modificar = true;
    this.tituloTema.enable();
    this.etiquetaTema.enable();
    this.infoTema.enable();
    this.decisionTema.enable();
  }

  modificarTema(form: NgForm, modal) {
    this.tema.titulo = form.value.tituloTema;
    this.tema.etiqueta = form.value.etiquetaTema;
    this.tema.info = form.value.infoTema;
    this.tema.decision = form.value.decisionTema;

    this.ts.modificarTema(this.tema).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Tema modificado con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.getAllTemasByCodSReunion(this.codsreunion);
      this.cerrarModalTema(modal);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al modificar el tema',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      console.log(error);
      this.cerrarModalTema(modal);
    });

  }

  /** MODAL PARA LAS TAREAS */

  verTarea(id: number, modal) {
    this.tarS.getTareaByCodTarea(id).subscribe(data => {
      this.tarea = data;

      this.tituloTarea.disable();
      this.descripTarea.disable();

      this.tituloTarea.setValue(this.tarea.titulo);
      this.descripTarea.setValue(this.tarea.descripcion);
    });

    this.us.getUsuariosNotInTarea(this.codsreunion, id).subscribe(data => {
      this.usuariosnotintarea = data;
    });

    this.modalService.open(modal);
  }

  cerrarModalTarea(modal) {
    this.infoTareaForm.reset();
    this.modalService.dismissAll(modal);
    this.nomodificar = true;
    this.modificar = false;
  }

  siTarea() {
    this.nomodificar = false;
    this.modificar = true;
    this.tituloTarea.enable();
    this.descripTarea.enable();
  }

  modificarTarea(form: NgForm, modal) {
    let codigos = [-1];
    this.tarea.titulo = form.value.tituloTarea;
    this.tarea.descripcion = form.value.descripTarea;

    if (form.value.responsablesTarea !== '') {
      codigos = form.value.responsablesTarea;
    }

    this.tarS.modificarTarea(this.tarea, codigos).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Tarea modificada con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.getAllTareasByCodSReunion(this.codsreunion);
      this.cerrarModalTarea(modal);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al modificar la tarea',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      console.log(error);
      this.cerrarModalTarea(modal);
    });

  }

  /** GET atributos del formulario para los TEMAS */

  get tituloTema() {
    return this.infoTemaForm.get('tituloTema');
  }

  get etiquetaTema() {
    return this.infoTemaForm.get('etiquetaTema');
  }

  get infoTema() {
    return this.infoTemaForm.get('infoTema');
  }

  get decisionTema() {
    return this.infoTemaForm.get('decisionTema');
  }

  /** GET atributos del formulario para las TAREAS */

  get tituloTarea() {
    return this.infoTareaForm.get('tituloTarea');
  }

  get descripTarea() {
    return this.infoTareaForm.get('descripTarea');
  }

}
