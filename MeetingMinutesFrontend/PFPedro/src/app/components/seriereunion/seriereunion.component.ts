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

@Component({
  selector: 'app-seriereunion',
  templateUrl: './seriereunion.component.html',
  styleUrls: ['./seriereunion.component.scss']
})
export class SeriereunionComponent implements OnInit {

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

  constructor(public route: ActivatedRoute, private sr: SeriereunionService, public us: UsuarioService, public rs: ReunionService,
              public ts: TemasService, public tarS: TareasService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.sr.getSerieReunionByCodSReunion(this.codsreunion).subscribe(data => {
          this.serieReunion = data;
      });
      this.us.getUsuariosInSerieReunion(this.codsreunion).subscribe(data => {
        this.usuarios = data;
      });
      this.ts.getAllTemasByCodSReunion(this.codsreunion).subscribe(data => {
        this.temas = data;
      });
      this.tarS.getAllTareasByCodSReunion(this.codsreunion).subscribe(data => {
        this.tareas = data;
      });
    });
    this.getReuniones();
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

}
