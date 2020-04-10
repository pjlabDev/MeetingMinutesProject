import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReunionService } from '../../services/reunion.service';
import { TemasService } from '../../services/temas.service';
import { UsuarioService } from '../../services/usuario.service';
import { SeriereunionService } from '../../services/seriereunion.service';
import { SerieReunion } from '../../clases/serie-reunion';
import { Usuario } from '../../clases/usuario';
import { Reunion } from '../../clases/reunion';
import { Temas } from '../../clases/temas';
import { EmailService } from '../../services/email.service';
import { DatePipe } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-reunion',
  templateUrl: './reunion.component.html',
  styleUrls: ['./reunion.component.scss'],
  providers: [DatePipe]
})
export class ReunionComponent implements OnInit {

  reunion: Reunion = new Reunion();
  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  codsreunion: number;
  codreunion: number;
  temas: Temas[];
  fechaReunion: string;
  codigos: number[] = [];

  constructor(public route: ActivatedRoute, public us: UsuarioService, public sr: SeriereunionService,
              public ts: TemasService, public rs: ReunionService, public router: Router, public es: EmailService,
              public datepipe: DatePipe) { }

  ngOnInit() {
    this.route.paramMap.subscribe(response => {
      this.codsreunion = parseInt(response.get('id'), 10);
      this.codreunion = parseInt(response.get('idd'), 10);
      this.getSerieReunionById(this.codsreunion);
      this.rs.getReunionByCodReunion(this.codreunion).subscribe(data => {
        this.reunion = data;
      });
      this.getTemas(this.codsreunion);
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

  getTemas(id: number) {
    this.ts.getTemasBySerieReunion(id).subscribe(data => {
      this.temas = data;
    });
  }

  enviarAgenda() {
    this.fechaReunion = this.datepipe.transform(this.reunion.fecha, 'dd-MM-yyyy');

    this.usuarios.forEach(response => {
      this.codigos.push(response.codUsu);
    });

    this.es.enviarAgenda(this.codigos, this.fechaReunion, this.temas).subscribe(dat => {
        Swal.fire({
          icon: 'success',
          title: 'Agenda enviada a los participantes de esta reunión.',
          showConfirmButton: false,
          timer: 1500
        });
      }, error => {
        console.log('Error al enviar la agenda', error);
      });


  }

}
