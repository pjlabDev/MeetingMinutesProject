import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Temas } from '../../clases/temas';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { SeriereunionService } from '../../services/seriereunion.service';
import Swal from 'sweetalert2';
import { Usuario } from '../../clases/usuario';
import { Tareas } from '../../clases/tareas';
import { TareasService } from '../../services/tareas.service';

@Component({
  selector: 'app-tareas',
  templateUrl: './tareas.component.html',
  styleUrls: ['./tareas.component.scss']
})
export class TareasComponent implements OnInit {

  submitted = false;
  existeTarea = false;
  nuevaTareaForm = new FormGroup({
    titulo: new FormControl('', [Validators.required]),
    descripcion: new FormControl('', [Validators.required]),
    responsables: new FormControl('', [Validators.required])
  });
  usuarios: Usuario[];
  tareas: Tareas[];
  tarea: Tareas = new Tareas();
  codsreunion: number;
  codreunion: number;
  codusu: number[];

  constructor(public route: ActivatedRoute, public us: UsuarioService, private router: Router, public ts: TareasService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.codreunion = parseInt(response.get('idd'), 10);
      this.getTareas(this.codreunion);
      this.us.getUsuariosByCodReunion(this.codreunion).subscribe(data => {
        this.usuarios = data;
      });
    });
  }

  getTareas(id: number) {
    this.ts.getTareasByCodReunion(id).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.existeTarea = true;
        this.tareas = data;
      } else {
        this.existeTarea = false;
      }
    });
  }

  submittedTrue() {
    this.submitted = true;
  }

  crearTarea(form: NgForm) {
    this.tarea.titulo = form.value.titulo;
    this.tarea.descripcion = form.value.descripcion;
    this.codusu = form.value.responsables;

    this.ts.crearTareas(this.tarea, this.codreunion, this.codusu).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Tarea creado con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.submitted = false;
      this.nuevaTareaForm.reset();
      this.getTareas(this.codreunion);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al crear la tarea',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.nuevaTareaForm.reset();
      console.log('Error al crear reunion', error);
    });
  }


}
