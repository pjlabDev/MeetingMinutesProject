import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UsuarioService } from '../services/usuario.service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class RolGuard implements CanActivate {
  constructor(private router: Router, private user: UsuarioService) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this.user.isUserAdminAndJefe()) {
      Swal.fire({
        title: 'Error!',
        text: 'No tienes permitido entrar en esta sección, debes ser Administrador o Jefe de reunión. Te rederigimos al home.',
        icon: 'error',
        confirmButtonText: 'Aceptar',
        preConfirm: () => {
          return this.router.navigate(['home']);
        }
      });
      return false;
    } else {
      return true;
    }
  }

}
