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


  logout() {
    this.us.logOut();
    this.router.navigate(['login']);
  }

}
