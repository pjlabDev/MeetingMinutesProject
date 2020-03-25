import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Usuario } from '../../clases/usuario';
import { ApiService } from '../../services/api.service';
import { SeriereunionService } from '../../services/seriereunion.service';
import { SerieReunion } from '../../clases/serie-reunion';

@Component({
  selector: 'app-nuevaseriereunion',
  templateUrl: './nuevaseriereunion.component.html',
  styleUrls: ['./nuevaseriereunion.component.scss']
})
export class NuevaseriereunionComponent implements OnInit {

  mensajeError = false;
  serieReunion: SerieReunion = new SerieReunion();
  usuarios: Usuario[];
  newSerieReunionForm = new FormGroup({
    equipo: new FormControl('', [Validators.required, Validators.minLength(3)]),
    nombre: new FormControl('', [Validators.required, Validators.minLength(5)]),
    usuario: new FormControl('', [Validators.required])
  });

  constructor(private api: ApiService, private sr: SeriereunionService) {
    this.usuarios = [];
  }

  ngOnInit() {
    this.api.getAllUsuarios().subscribe(data => {
      this.usuarios = data;
    });
  }

  crearSerieReunion(form: NgForm) {
      console.log('Equipo', form.value.equipo);
      console.log('Nombre', form.value.nombre);
      console.log('Codigo usuario', form.value.usuario);

      this.serieReunion.equipo = form.value.equipo;
      this.serieReunion.nombre = form.value.nombre;

      this.sr.crearSerieReunion(this.serieReunion, parseInt(form.value.usuario, 10)).subscribe(data => {
        alert('Serie Reunion añadida con éxito.');
        this.newSerieReunionForm.reset();
      }, error => {
        this.mensajeError = true;
        setTimeout(() => {
        this.mensajeError = false;
      }, 3000);
        this.newSerieReunionForm.reset();
        console.log('Error al crear reunion', error);
      });

  }


  get equipo() {
    return this.newSerieReunionForm.get('equipo');
  }
  get nombre() {
    return this.newSerieReunionForm.get('nombre');
  }
  get usuario() {
    return this.newSerieReunionForm.get('usuario');
  }

}
