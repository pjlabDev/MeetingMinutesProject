import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { EmailService } from '../../services/email.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {

  comentarioForm = new FormGroup({
    nombre: new FormControl('', [Validators.required]),
    correo: new FormControl('', [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')]),
    comentario: new FormControl('', [Validators.required, Validators.maxLength(500)])
  });

  constructor(public es: EmailService) { }

  ngOnInit() {
  }

  enviarComentario(form: NgForm) {

    const nombre = form.value.nombre;

    const correo = form.value.correo;

    const comentario = form.value.comentario;

    this.es.enviarComentario(nombre, correo, comentario).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Comentario enviado con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.comentarioForm.reset();
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al enviar el comentario.',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
    });
  }

  get nombre() {
    return this.comentarioForm.get('nombre');
  }

  get correo() {
    return this.comentarioForm.get('correo');
  }

  get comentario() {
    return this.comentarioForm.get('comentario');
  }

}
