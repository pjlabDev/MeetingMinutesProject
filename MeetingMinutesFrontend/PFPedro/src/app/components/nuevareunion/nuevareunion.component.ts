import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { SeriereunionService } from '../../services/seriereunion.service';
import { SerieReunion } from '../../clases/serie-reunion';
import { Usuario } from '../../clases/usuario';

@Component({
  selector: 'app-nuevareunion',
  templateUrl: './nuevareunion.component.html',
  styleUrls: ['./nuevareunion.component.scss']
})
export class NuevareunionComponent implements OnInit {

  submitted = false;

  nuevaReunionform = new FormGroup({
    fecha: new FormControl('', [Validators.required]),
    participantes: new FormControl(''),
    archivos: new FormControl('')
  });

  nuevoTemaform = new FormGroup({
    titulo: new FormControl()
  });

  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  codsreunion: number;

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.getSerieReunionById(this.codsreunion);
      this.getUsuariosBySerieReunion(this.codsreunion);
    });
  }


  getSerieReunionById(id: number) {
    this.sr.getSerieReunionByCodReunion(id).subscribe(data => {
      this.serieReunion = data;
    }, error => {
      console.log('Error al recibir la serieReunion: ', error);
    });
  }

  getUsuariosBySerieReunion(id: number) {
    this.us.getUsuariosInReunion(id).subscribe(data => {
      this.usuarios = data;
    }, error => {
      console.log('Error al recibir usuarios', error);
    });
  }

  submittedTrue() {
    this.submitted = true;
  }

}
