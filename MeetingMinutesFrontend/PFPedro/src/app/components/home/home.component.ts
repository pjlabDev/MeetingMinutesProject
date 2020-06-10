import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../clases/usuario';
import { SerieReunion } from '../../clases/serie-reunion';
import { SeriereunionService } from '../../services/seriereunion.service';
import { FormGroup, FormControl, NgForm } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  /** Variables para recoger los datos */

  usuario: Usuario = new Usuario();
  serieReuniones: SerieReunion[];

  /** FormGroup asociado al fromulario del html */

  cerrarForm = new FormGroup({
    codsreunion: new FormControl(''),
  });

  filtroBusqueda;

  constructor(public us: UsuarioService, public sr: SeriereunionService) {
    this.serieReuniones = [];
  }

  /** Recogida de Serie de Reuniones por usuario */

  ngOnInit() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.us.isUserLoggedIn();
    this.sr.getSerieReunionByUsuario(this.usuario.codUsu).subscribe(data => {
      this.serieReuniones = data;
    }, error => {
      console.log(error);
    });
  }

  get codsreunion() {
    return this.cerrarForm.get('codsreunion');
  }

}
