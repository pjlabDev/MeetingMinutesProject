import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Usuario } from '../../clases/usuario';
import { SeriereunionService } from '../../services/seriereunion.service';
import { SerieReunion } from '../../clases/serie-reunion';
import Swal from 'sweetalert2';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-nuevaseriereunion',
  templateUrl: './nuevaseriereunion.component.html',
  styleUrls: ['./nuevaseriereunion.component.scss']
})
export class NuevaseriereunionComponent implements OnInit {

  mensajeError = false;
  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  codigos: number[];
  newSerieReunionForm = new FormGroup({
    equipo: new FormControl('', [Validators.required, Validators.minLength(3)]),
    nombre: new FormControl('', [Validators.required, Validators.minLength(5)]),
    usuario: new FormControl('', [Validators.required])
  });

  constructor(private us: UsuarioService, private sr: SeriereunionService) {
    this.usuarios = [];
  }

  /** Recoge todos los usuarios registrados. */

  ngOnInit() {
    this.us.getAllUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

  /** Crea una nueva Serie de Reuniones */

  crearSerieReunion(form) {
      this.serieReunion.equipo = form.value.equipo;
      this.serieReunion.nombre = form.value.nombre;
      this.codigos = form.value.usuario;

      this.sr.crearSerieReunion(this.serieReunion, this.codigos).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Serie de reuniones creada con éxito.',
          showConfirmButton: false,
          timer: 1500
        });
        this.newSerieReunionForm.reset();
      }, error => {
        Swal.fire({
          icon: 'error',
          title: 'Lo sentimos, ha ocurrido un problema al crear la reunion',
          text: 'Puede que el usuario seleccionado no exista en base de datos.',
          timer: 1500
        });
        this.newSerieReunionForm.reset();
        console.log('Error al crear reunion', error);
      });

  }


  /** Getters para recoger los campos del formulario */

  get equipo() {
    return this.newSerieReunionForm.get('equipo');
  }
  get nombre() {
    return this.newSerieReunionForm.get('nombre');
  }
  get usuario() {
    return this.newSerieReunionForm.get('usuario');
  }

}
