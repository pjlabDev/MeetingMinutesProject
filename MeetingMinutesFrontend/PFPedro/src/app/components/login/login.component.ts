import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators, NgForm} from '@angular/forms';
import { Usuario } from '../../clases/usuario';
import { ApiService } from '../../services/api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  submitted = false;
  mensaje = false;
  userForm = new FormGroup({
    nombre: new FormControl('', [Validators.required]),
    clave: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  constructor(private api: ApiService, private router: Router) { }

  ngOnInit() {
    this.submitted = false;
  }


  userLogin(form: NgForm) {
    this.api.loginUsuario(form.value.nombre, form.value.clave)
      .subscribe(data => {
        if (data === null) {
          this.mensaje = true;
          setTimeout(() => {
            this.mensaje = false;
          }, 3000);
        } else {
          if (form.value.clave !== data.clave && form.value.nombre !== data.nombre) {
              this.mensaje = true;
              setTimeout(() => {
              this.mensaje = false;
            }, 3000);
          } else {
              console.log('Login correcto!!');
              this.router.navigate(['home']);
          }
        }
      }, error => console.log(error));
  }

  get clave() {
    return this.userForm.get('clave');
  }
  get nombre() {
    return this.userForm.get('nombre');
  }

}
