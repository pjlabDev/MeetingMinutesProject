import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from './services/usuario.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'PFPedro';

  constructor(public us: UsuarioService,
              private router: Router) { }

  policy = true;

  /** Método para cerrar sesión en la página */

  logout() {
    this.us.logOut();
    this.router.navigate(['login']);
  }

  aceptarNoAceptar() {
    this.policy = false;
  }

  leerMas() {
    window.open('../assets/pdf/Policy_MM.pdf' + '#page=' + 1, '_blank', '', true);
  }

}
