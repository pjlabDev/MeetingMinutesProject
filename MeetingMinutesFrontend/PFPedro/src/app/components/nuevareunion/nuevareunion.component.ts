import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { SeriereunionService } from '../../services/seriereunion.service';
import { SerieReunion } from '../../clases/serie-reunion';
import { Usuario } from '../../clases/usuario';
import { TemasService } from '../../services/temas.service';
import Swal from 'sweetalert2';
import { Reunion } from '../../clases/reunion';
import { ReunionService } from '../../services/reunion.service';

@Component({
  selector: 'app-nuevareunion',
  templateUrl: './nuevareunion.component.html',
  styleUrls: ['./nuevareunion.component.scss']
})
export class NuevareunionComponent implements OnInit {

  nuevaReunionform = new FormGroup({
    fecha: new FormControl('', [Validators.required]),
    participantes: new FormControl('', [Validators.required])
  });

  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  codsreunion: number;
  reunion: Reunion = new Reunion();
  codigos: number[];

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, public rs: ReunionService, public router: Router) { }

  /** Recoge la Serie de Reunion y los usuarios participantes de la misma */

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.getSerieReunionById(this.codsreunion);
      this.getUsuariosBySerieReunion(this.codsreunion);
    });
  }


  getSerieReunionById(id: number) {
    this.sr.getSerieReunionByCodSReunion(id).subscribe(data => {
      this.serieReunion = data;
    }, error => {
      console.log('Error al recibir la serieReunion: ', error);
    });
  }

  getUsuariosBySerieReunion(id: number) {
    this.us.getUsuariosInSerieReunion(id).subscribe(data => {
      this.usuarios = data;
    }, error => {
      console.log('Error al recibir usuarios', error);
    });
  }

  /** Método para crear una nueva Reunión */

  crearReunion(form) {
    this.reunion.fecha = form.value.fecha;
    this.codigos = form.value.participantes;
    this.rs.crearReunion(this.reunion, this.codsreunion, this.codigos).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Reunión creada con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.nuevaReunionform.reset();
      this.router.navigate(['/', 'seriereunion', this.codsreunion]);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema a la hora de crear la reunión.',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.nuevaReunionform.reset();
      console.log('Error al crear reunion', error);
    });
  }

}
