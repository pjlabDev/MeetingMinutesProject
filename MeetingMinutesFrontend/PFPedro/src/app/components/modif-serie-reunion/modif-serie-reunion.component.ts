import { Component, OnInit } from '@angular/core';
import { SerieReunion } from '../../clases/serie-reunion';
import { SeriereunionService } from '../../services/seriereunion.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from '../../clases/usuario';
import { FormGroup, FormControl, NgForm, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-modif-serie-reunion',
  templateUrl: './modif-serie-reunion.component.html',
  styleUrls: ['./modif-serie-reunion.component.scss']
})
export class ModifSerieReunionComponent implements OnInit {

  serieReunion: SerieReunion = new SerieReunion();
  codsreunion: number;
  usuarios: Usuario[];
  invitar = false;
  codigos: number[];
  modifSerieReunionForm = new FormGroup({
    equipo: new FormControl('', [Validators.required, Validators.minLength(3)]),
    nombre: new FormControl('', [Validators.required, Validators.minLength(5)]),
    usuario: new FormControl(''),
    cerrado: new FormControl('', [Validators.required])
  });

  constructor(public route: ActivatedRoute, private sr: SeriereunionService, public us: UsuarioService, private router: Router) { }

  /** Recoge la serie de reuniones por codigo y los usuarios que no estén en ella */

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.getSerieReunion(this.codsreunion);
      this.getUsuariosNotInSerieReunion(this.codsreunion);
    });
  }

  getSerieReunion(id: number) {
      this.sr.getSerieReunionByCodSReunion(id).subscribe(data => {
        this.serieReunion = data;
        this.equipo.setValue(this.serieReunion.equipo);
        this.nombre.setValue(this.serieReunion.nombre);
        this.cerrado.setValue(this.serieReunion.cerrado);
    });
  }

  getUsuariosNotInSerieReunion(id: number) {
    this.us.getUsuariosNotInSerieReunion(id).subscribe(data => {
      this.usuarios = data;
    });
  }

  /** Método para modificar la Serie de Reunion */

  modifSerieReunion(form) {
      this.codigos = form.value.usuario;

      this.serieReunion.equipo = form.value.equipo;
      this.serieReunion.nombre = form.value.nombre;
      this.serieReunion.cerrado = form.value.cerrado;

      if (this.codigos.length > 0) {
          this.sr.modifSerieReunionConInvitado(this.serieReunion, this.codigos).subscribe(data => {
            Swal.fire({
              icon: 'success',
              title: 'Seriereunion con mas invitados modificada con exito.',
              showConfirmButton: false,
              timer: 1500
            });
            this.router.navigate(['home']);
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Lo sentimos, ha ocurrido un problema al modificar la reunión con más invitados.',
            text: 'Si el error persiste, pruebe a reiniciar la página y vuelva a intenarlo más tarde.',
            timer: 1500
          });
          console.log('Error al modificar la reunion con mas invitados: ', error);
        });
      } else {
        this.sr.modifSerieReunion(this.serieReunion).subscribe(data => {
          Swal.fire({
            icon: 'success',
            title: 'Seriereunion modificada con éxito.',
            showConfirmButton: false,
            timer: 1500
          });
          this.router.navigate(['home']);
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Lo sentimos, ha ocurrido un problema al modificar la reunión',
            text: 'Si el error persiste, pruebe a reiniciar la página y vuelva a intenarlo más tarde.',
            timer: 1500
          });
          console.log('Error al modificar reunion: ', error);
        });
      }
  }

  /** Método para eliminar participantes de la Serie de Reunion */

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
        this.sr.eliminarParticipante(this.serieReunion, codusu).subscribe(data => {
          Swal.fire(
            'Eliminado!',
            'Participante eliminado.',
            'success'
          );
          this.getSerieReunion(this.codsreunion);
          this.getUsuariosNotInSerieReunion(this.codsreunion);
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

  /** Geters para recoger los campos del formulario */

  get nombre() {
    return this.modifSerieReunionForm.get('nombre');
  }

  get equipo() {
    return this.modifSerieReunionForm.get('equipo');
  }

  get cerrado() {
    return this.modifSerieReunionForm.get('cerrado');
  }

}

