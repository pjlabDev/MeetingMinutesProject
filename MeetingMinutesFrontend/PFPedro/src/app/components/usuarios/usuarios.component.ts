import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { Usuario } from '../../clases/usuario';
import { FormGroup, FormControl, NgForm, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[];
  user: Usuario = new Usuario();
  pageActual = 1;
  userForm = new FormGroup({
    nombre: new FormControl('', [Validators.required, Validators.minLength(6)]),
    correo: new FormControl('', [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')]),
    clave: new FormControl('', [Validators.required, Validators.minLength(6)]),
    rol: new FormControl(''),
    rolacambiar: new FormControl('')
  });
  modificar = false;
  nomodificar = true;

  constructor(public us: UsuarioService, private modalService: NgbModal) { }

  ngOnInit() {
    this.getAllUsuarios();
  }

  getAllUsuarios() {
    this.us.getAllUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

  verUsuario(id: number, modal) {
    this.us.getUserByCodUsu(id).subscribe(data => {
      this.user = data;
      this.nombre.setValue(this.user.nombre);
      this.correo.setValue(this.user.correo);
      this.clave.setValue(this.user.clave);
      this.rol.setValue(this.user.rol);
      this.rolacambiar.setValue('Seleccione un rol');
      this.rol.disable();
    });
    this.modalService.open(modal);
  }

  modificarUsuario(form: NgForm, modal) {
    this.user.nombre = form.value.nombre;
    this.user.correo = form.value.correo;
    this.user.clave = form.value.clave;
    if (form.value.rolacambiar !== 'Seleccione un rol') {
      this.user.rol = parseInt(form.value.rolacambiar, 10);
    }

    this.us.modificarUsuario(this.user).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Usuario modificado con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.getAllUsuarios();
      this.cerrarModalUser(modal);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al modificar el usuario',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      console.log(error);
      this.cerrarModalUser(modal);
    });

  }

  cerrarModalUser(modal) {
    this.modalService.dismissAll(modal);
    this.userForm.reset();
  }

  eliminarUsuario(codusu: number) {
    this.us.eliminarUsuario(codusu).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Usuario eliminado con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.getAllUsuarios();
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al modificar el usuario',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      console.log(error);
    });
  }

  get nombre() {
    return this.userForm.get('nombre');
  }

  get correo() {
    return this.userForm.get('correo');
  }

  get clave() {
    return this.userForm.get('clave');
  }

  get rol() {
    return this.userForm.get('rol');
  }

  get rolacambiar() {
    return this.userForm.get('rolacambiar');
  }

}
