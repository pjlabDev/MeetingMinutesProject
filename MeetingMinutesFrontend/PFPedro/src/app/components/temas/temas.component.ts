import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TemasService } from '../../services/temas.service';
import { UsuarioService } from '../../services/usuario.service';
import { SeriereunionService } from '../../services/seriereunion.service';
import { FormGroup, Validators, FormControl, NgForm } from '@angular/forms';
import { Temas } from '../../clases/temas';
import Swal from 'sweetalert2';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


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

  infoForm = new FormGroup({
    info: new FormControl('', [Validators.required])
  });

  temas: Temas[];
  codsreunion: number;
  tema: Temas = new Temas();
  codTema: number;

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, private router: Router, private modalService: NgbModal) { }

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
      console.log('Error al crear tema.', error);
    });
  }

  verModal(codTema: number, modal) {
    this.modalService.open(modal);
    this.codTema = codTema;
  }

  cerrarModal(modal) {
    this.infoForm.reset();
    this.modalService.dismissAll(modal);
  }

  modificarTema(form: NgForm, modal) {
    console.log(form.value.info);
    console.log(this.codTema);
    this.tema.info = form.value.info;
    console.log('Tema', this.tema);

    this.ts.añadirInfoTema(this.tema, this.codTema).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Información para el tema añadida con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.infoForm.reset();
      this.getTemas(this.codsreunion);
      this.modalService.dismissAll(modal);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al añadir información al tema',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.nuevoTemaform.reset();
      console.log('Error al añadir información al tema.', error);
    });

  }

}
