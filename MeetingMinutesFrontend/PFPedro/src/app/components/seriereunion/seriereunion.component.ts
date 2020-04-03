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

@Component({
  selector: 'app-seriereunion',
  templateUrl: './seriereunion.component.html',
  styleUrls: ['./seriereunion.component.scss']
})
export class SeriereunionComponent implements OnInit {

  codsreunion: number;
  usuarios: Usuario[];
  reunion: Reunion[];
  serieReunion: SerieReunion = new SerieReunion();
  isReunion = false;
  isTema = false;
  isItems = false;
  temas: Temas[];

  constructor(public route: ActivatedRoute, private sr: SeriereunionService, public us: UsuarioService, public rs: ReunionService,
              public ts: TemasService) {
                this.isReunion = true;
    }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.sr.getSerieReunionByCodReunion(this.codsreunion).subscribe(data => {
          this.serieReunion = data;
      });
      this.us.getUsuariosInReunion(this.codsreunion).subscribe(data => {
        this.usuarios = data;
      });
      this.rs.getReunionBySerieReunion(this.codsreunion).subscribe(data => {
        this.reunion = data;
      });
      this.ts.getTemasBySerieReunion(this.codsreunion).subscribe(data => {
        this.temas = data;
      });
    });
  }


  verReuniones() {
    this.isTema = false;
    this.isReunion = true;
  }

  verTemas() {
    this.isReunion = false;
    this.isTema = true;
  }

  verItems() {
    this.isItems = true;
  }

}
