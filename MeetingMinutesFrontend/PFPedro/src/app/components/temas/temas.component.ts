import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TemasService } from '../../services/temas.service';
import { UsuarioService } from '../../services/usuario.service';
import { SeriereunionService } from '../../services/seriereunion.service';
import { FormGroup, Validators, FormControl, NgForm } from '@angular/forms';
import { SerieReunion } from '../../clases/serie-reunion';
import { Usuario } from '../../clases/usuario';
import { Temas } from '../../clases/temas';
import Swal from 'sweetalert2';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-temas',
  templateUrl: './temas.component.html',
  styleUrls: ['./temas.component.scss']
})
export class TemasComponent implements OnInit {

  submitted = false;
  existeTema = false;

  nuevoTemaform = new FormGroup({
    titulo: new FormControl('', [Validators.required]),
    etiqueta: new FormControl('')
  });

  temas: Temas[];
  codsreunion: number;
  tema: Temas = new Temas();

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.getTemas(this.codsreunion);
    });
  }

  getTemas(id: number) {
    this.ts.getTemasBySerieReunion(id).subscribe(data => {
      if (data !== null) {
        this.existeTema = true;
        this.temas = data;
      } else {
        this.existeTema = false;
      }
    });
  }

  submittedTrue() {
    this.submitted = true;
  }

  crearTema(form: NgForm) {
    this.tema.titulo = form.value.titulo;
    this.tema.etiqueta = form.value.etiqueta;
    console.log(this.tema.etiqueta);
    this.ts.crearTemas(this.tema, this.codsreunion).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Tema creado con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.submitted = false;
      this.nuevoTemaform.reset();
      this.getTemas(this.codsreunion);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al crear el tema',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.nuevoTemaform.reset();
      console.log('Error al crear reunion', error);
    });
  }

}
