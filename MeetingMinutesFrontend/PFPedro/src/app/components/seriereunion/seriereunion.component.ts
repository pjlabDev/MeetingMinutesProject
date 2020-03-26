import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SeriereunionService } from '../../services/seriereunion.service';
import { SerieReunion } from '../../clases/serie-reunion';
import { ApiService } from '../../services/api.service';
import { Usuario } from '../../clases/usuario';

@Component({
  selector: 'app-seriereunion',
  templateUrl: './seriereunion.component.html',
  styleUrls: ['./seriereunion.component.scss']
})
export class SeriereunionComponent implements OnInit {

  codsreunion: number;
  usuarios: Usuario[];
  serieReunion: SerieReunion = new SerieReunion();

  constructor(public route: ActivatedRoute, private sr: SeriereunionService, private api: ApiService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.sr.getSerieReunionByCodReunion(this.codsreunion).subscribe(data => {
          this.serieReunion = data;
      });
      this.api.getUsuariosInReunion(this.codsreunion).subscribe(data => {
        this.usuarios = data;
      });
    });
  }

}
