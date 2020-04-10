import { Component, OnInit } from '@angular/core';
import { Reunion } from '../../clases/reunion';
import { TemasService } from '../../services/temas.service';
import { ReunionService } from '../../services/reunion.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SeriereunionService } from '../../services/seriereunion.service';
import { UsuarioService } from '../../services/usuario.service';
import { SerieReunion } from '../../clases/serie-reunion';
import { Usuario } from '../../clases/usuario';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Acta } from '../../clases/acta';
import { ActaService } from '../../services/acta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-acta',
  templateUrl: './acta.component.html',
  styleUrls: ['./acta.component.scss']
})
export class ActaComponent implements OnInit {

  reunion: Reunion = new Reunion();
  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  codsreunion: number;
  codreunion: number;
  codigos: number[] = [];
  acta: Acta = new Acta();

  nuevaActaForm = new FormGroup({
    fecha: new FormControl('', [Validators.required]),
    asistentes: new FormControl('', [Validators.required])
  });

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, public rs: ReunionService, public router: Router, public as: ActaService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.codreunion = parseInt(response.get('idd'), 10);
      this.getSerieReunionById(this.codsreunion);
      this.rs.getReunionByCodReunion(this.codreunion).subscribe(data => {
        this.reunion = data;
      });
      this.us.getUsuariosByCodReunion(this.codreunion).subscribe(data => {
        this.usuarios = data;
      });
    });
  }

  getSerieReunionById(id: number) {
    this.sr.getSerieReunionByCodReunion(id).subscribe(data => {
      this.serieReunion = data;
    }, error => {
      console.log('Error al recibir la serieReunion: ', error);
    });
  }

  generarActa(form: NgForm) {
    this.acta.fecha = form.value.fecha;
    this.codigos = form.value.asistentes;

    this.as.generarActa(this.acta, this.codreunion, this.codigos).subscribe(data => {
      Swal.fire({
        icon: 'success',
        title: 'Acta generada con éxito.',
        showConfirmButton: false,
        timer: 1500
      });
      this.nuevaActaForm.reset();
      this.router.navigate(['/', 'reunion', this.codsreunion, this.codreunion]);
    }, error => {
      Swal.fire({
        icon: 'error',
        title: 'Lo sentimos, ha ocurrido un problema a la hora de crear la reunión.',
        text: 'Inténtelo de nuevo o mas tarde.',
        timer: 1500
      });
      this.nuevaActaForm.reset();
      console.log('Error al generar acta.', error);
    });
  }


}
