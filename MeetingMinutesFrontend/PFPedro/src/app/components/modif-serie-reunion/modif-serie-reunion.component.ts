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
    equipo: new FormControl('', [Validators.required]),
    nombre: new FormControl('', [Validators.required]),
    usuario: new FormControl(''),
    cerrado: new FormControl('', [Validators.required])
  });

  constructor(public route: ActivatedRoute, private sr: SeriereunionService, private us: UsuarioService, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.sr.getSerieReunionByCodSReunion(this.codsreunion).subscribe(data => {
          this.serieReunion = data;
      });
      this.us.getUsuariosNotInSerieReunion(this.codsreunion).subscribe(data => {
          this.usuarios = data;
      });
    });
  }

  get diagnostic() { return JSON.stringify(this.serieReunion); }

  modifSerieReunion(reunion: SerieReunion, form: NgForm) {
      this.codigos = form.value.usuario;

      if (this.codigos.length > 0) {
          this.sr.modifSerieReunionConInvitado(reunion, this.codigos).subscribe(data => {
            Swal.fire({
              icon: 'success',
              title: 'Reunion con mas invitados modificada con exito.',
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
        this.sr.modifSerieReunion(reunion).subscribe(data => {
          Swal.fire({
            icon: 'success',
            title: 'Reunion modificada con éxito.',
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

}
