import { Component, OnInit } from '@angular/core';
import { Usuario } from '../../clases/usuario';
import { LoginComponent } from '../login/login.component';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  usuario: Usuario = new Usuario();

  constructor(public api: ApiService) { }

  ngOnInit() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    this.api.isUserLoggedIn();
  }

  isUserAdmin() {
    if (this.usuario.rol === 'ADMINISTRADOR') {
      return true;
    } else {
      return false;
    }
  }

}
