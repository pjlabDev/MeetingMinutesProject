import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { Usuario } from '../../clases/usuario';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-nuevaseriereunion',
  templateUrl: './nuevaseriereunion.component.html',
  styleUrls: ['./nuevaseriereunion.component.scss']
})
export class NuevaseriereunionComponent implements OnInit {

  mensajeError = false;
  usuario: Usuario = new Usuario();
  newSerieReunionForm = new FormGroup({
    equipo: new FormControl('', [Validators.required, Validators.maxLength(20)]),
    nombre: new FormControl('', [Validators.required, Validators.maxLength(20)]),
  });

  constructor(private api: ApiService) { }

  ngOnInit() {
  }

  addNuevaSerieReunion() {

  }

  get equipo() {
    return this.newSerieReunionForm.get('equipo');
  }
  get nombre() {
    return this.newSerieReunionForm.get('nombre');
  }

}
