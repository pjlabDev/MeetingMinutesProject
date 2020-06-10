import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class LetrasGuard implements CanActivate {
  constructor(private router: Router) { }

  /** Método que nos ayudará a controlar que un usuario escriba bien la primera parte de la url sin numeros */

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    const letras = 'abcdefghyjklmnñopqrstuvwxyz';

    const id = next.paramMap.get('id');
    let num = 0;

    for (let i = 0; i < id.length; i++) {
      if (letras.indexOf(id.charAt(i), 0) !== -1) {
         num = 1;
      }
   }

    if (num !== 0) {
      Swal.fire({
        title: 'Error!',
        text: 'La url no es correcta, vuelva a intentarlo de nuevo.',
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
