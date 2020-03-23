import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { Usuario } from '../../clases/usuario';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  mensajeError = false;
  usuario: Usuario = new Usuario();
  registerForm = new FormGroup({
    correo: new FormControl('', [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')]),
    nombre: new FormControl('', [Validators.required, Validators.minLength(6)]),
    clave: new FormControl('', [Validators.required, Validators.minLength(6)]),
    rol: new FormControl('', [Validators.required])
  });

  constructor(private api: ApiService) { }

  ngOnInit() {
  }

  addNewUsuario(form: NgForm) {
    this.usuario.correo = form.value.correo;
    this.usuario.nombre = form.value.nombre;
    this.usuario.clave = form.value.clave;
    this.usuario.rol = parseInt(form.value.rol, 10);

    console.log(this.usuario);

    this.api.addNewUsuario(this.usuario).subscribe(data => {
      alert('Usuario añadido con éxito.');
      this.registerForm.reset();
    }, error => {
      console.log('Error al añadir', error);
      this.mensajeError = true;
      setTimeout(() => {
        this.mensajeError = false;
      }, 3000);
    });
  }

  get correo() {
    return this.registerForm.get('correo');
  }
  get nombre() {
    return this.registerForm.get('nombre');
  }
  get clave() {
    return this.registerForm.get('clave');
  }
  get rol() {
    return this.registerForm.get('rol');
  }

}
