import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UsuarioService } from '../services/usuario.service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private user: UsuarioService) { }

  /** Método que nos ayudará a controlar que un usuario esté logueado para poder acceder a las pestañas de la aplicación */

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.user.isUserLoggedIn()) {
      Swal.fire({
        title: 'Error!',
        text: 'No tienes permitido entrar en esta sección, antes debes loguearte. Te rederigimos al Login.',
        icon: 'error',
        confirmButtonText: 'Aceptar',
        preConfirm: () => {
          return this.router.navigate(['login'], { queryParams: { retUrl: route.url} });
        }
      });
      return false;
    } else {
      return true;
    }
  }

}
