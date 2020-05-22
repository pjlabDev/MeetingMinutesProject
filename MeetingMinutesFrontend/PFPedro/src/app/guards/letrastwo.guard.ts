import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class LetrastwoGuard implements CanActivate {
  constructor(private router: Router) { }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      const letras = 'abcdefghyjklmnñopqrstuvwxyz';

      const id = next.paramMap.get('id');
      const idd = next.paramMap.get('idd');

      let num = 0;
      let num2 = 0;

      for (let i = 0; i < id.length; i++) {
        if (letras.indexOf(id.charAt(i), 0) !== -1) {
           num = 1;
        }
     }

      for (let i = 0; i < idd.length; i++) {
      if (letras.indexOf(idd.charAt(i), 0) !== -1) {
         num2 = 1;
      }
   }

      if (num !== 0 || num2 !== 0) {
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
