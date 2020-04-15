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
  isItems = false;
  usuario: Usuario = new Usuario();

  constructor(public route: ActivatedRoute, private sr: SeriereunionService, public us: UsuarioService, public rs: ReunionService,
              public ts: TemasService, public tarS: TareasService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.sr.getSerieReunionByCodReunion(this.codsreunion).subscribe(data => {
          this.serieReunion = data;
      });
      this.us.getUsuariosInSerieReunion(this.codsreunion).subscribe(data => {
        this.usuarios = data;
      });
      this.ts.getTemasBySerieReunion(this.codsreunion).subscribe(data => {
        this.temas = data;
      });
      this.tarS.getAllTareas().subscribe(data => {
        this.tareas = data;
      });
    });
    this.getReunionByUsuario();
  }


  verReuniones() {
    this.isTema = false;
    this.isItems = false;
    this.isReunion = true;
  }

  verTemas() {
    this.isReunion = false;
    this.isItems = false;
    this.isTema = true;
  }

  verItems() {
    this.isReunion = false;
    this.isTema = false;
    this.isItems = true;
  }

  getReunionByUsuario() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.rs.getReunionByUsuarios(this.usuario.codUsu).subscribe(data => {
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
