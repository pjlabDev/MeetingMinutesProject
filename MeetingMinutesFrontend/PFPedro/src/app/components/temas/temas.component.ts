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
  existeTemaAntiguo = false;

  nuevoTemaform = new FormGroup({
    titulo: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    etiqueta: new FormControl('')
  });

  infoForm = new FormGroup({
    info: new FormControl('', [Validators.required, Validators.maxLength(255)])
  });

  decisionForm = new FormGroup({
    decision: new FormControl('', [Validators.required, Validators.maxLength(255)])
  });

  seguimientoForm = new FormGroup({
    seguimiento: new FormControl('', [Validators.required, Validators.maxLength(255)])
  });

  temas: Temas[];
  temasAntiguos: Temas[];
  codsreunion: number;
  codreunion: number;
  tema: Temas = new Temas();
  codTema: number;

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, private router: Router, private modalService: NgbModal) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.codreunion = parseInt(response.get('idd'), 10);
      this.getTemas(this.codreunion);
      this.getTemasAntiguosNoCerrados(this.codreunion, this.codsreunion);
    });
  }

  getTemas(id: number) {
    this.ts.getTemasByCodReunion(id).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.existeTema = true;
        this.temas = data;
      } else {
        this.existeTema = false;
      }
    });
  }

  getTemasAntiguosNoCerrados(id: number, id2: number) {
    this.ts.getTemasByCodReunionAntiguaAndNoCerrado(id, id2).subscribe(data => {
      if (data !== null && data.length !== 0) {
        this.temasAntiguos = data;
        if (this.temasAntiguos.length > 0) {
          this.ts.saveTemaAntiguo(this.temasAntiguos, this.codreunion).subscribe(res => {
            this.getTemas(this.codreunion);
          }, error => {
            console.log('Error al guardar tema antiguo: ', error);
            this.getTemas(this.codreunion);
          });
        }
      }
    });
  }

  submittedTrue() {
    this.submitted = true;
  }

  crearTema(form: NgForm) {
    this.tema.titulo = form.value.titulo;
    this.tema.etiqueta = form.value.etiqueta;
    this.ts.crearTemas(this.tema, this.codreunion, this.codsreunion).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Tema creado con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.submitted = false;
      this.nuevoTemaform.reset();
      this.getTemas(this.codreunion);
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
    this.decisionForm.reset();
    this.modalService.dismissAll(modal);
  }

  addInfoTema(form: NgForm, modal) {
    this.tema.info = form.value.info;
    this.ts.añadirInfoTema(this.tema, this.codTema).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Información para el tema añadida con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.infoForm.reset();
      this.getTemas(this.codreunion);
      this.modalService.dismissAll(modal);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al añadir información al tema',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.infoForm.reset();
      console.log('Error al añadir información al tema.', error);
    });

  }

  addDecisionTema(form: NgForm, modal) {
    this.tema.decision = form.value.decision;
    this.ts.añadirDecisionTemas(this.tema, this.codTema).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Decisión para el tema añadida con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.decisionForm.reset();
      this.getTemas(this.codreunion);
      this.modalService.dismissAll(modal);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al añadir la decisión al tema',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.decisionForm.reset();
      console.log('Error al añadir información al tema.', error);
    });

  }

  cerrarTemas(codtema: number) {

    this.tema = new Temas();
    this.tema.codTema = codtema;

    Swal.fire({
      title: '¿Estás seguro?',
      text: 'El tema se cerrará si aceptas.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, cerrar!'
    }).then((result) => {
      if (result.value) {
        this.ts.cerrarTemas(this.tema).subscribe(data => {
          Swal.fire(
            'Cerrado!',
            'El tema ha sido cerrado.',
            'success'
          );
          this.getTemas(this.codreunion);
        }, error => {
          Swal.fire({
            icon: 'error',
            title: 'Lo sentimos, ha ocurrido un problema al cerrar el tema',
            text: 'Inténtelo de nuevo o mas tarde.',
            timer: 1500
          });
          console.log('error de tema: ', error);
        });
      }
    });
  }

  addSeguimientoTemaCerrado(form: NgForm, modal) {
    this.tema.seguimiento = form.value.seguimiento;
    this.tema.codTema = this.codTema;

    this.ts.addSeguimientoTemaCerrado(this.tema).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Seguimiento para el tema añadida con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.seguimientoForm.reset();
      this.getTemas(this.codreunion);
      this.modalService.dismissAll(modal);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema al añadir el seguimiento al tema',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.seguimientoForm.reset();
    });
  }

  get titulo() {
    return this.nuevoTemaform.get('titulo');
  }

  get info() {
    return this.infoForm.get('info');
  }

  get dec() {
    return this.decisionForm.get('decision');
  }

  get seguimiento() {
    return this.seguimientoForm.get('seguimiento');
  }

}
