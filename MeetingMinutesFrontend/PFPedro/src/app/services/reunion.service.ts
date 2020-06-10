import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reunion } from '../clases/reunion';

@Injectable({
  providedIn: 'root'
})
export class ReunionService {

  private baseUrl = 'https://mm-project.herokuapp.com/reunion/';

  constructor(private http: HttpClient) { }

  /** Método para recoger las reuniones por código de la Serie de reuniones desde el Backend */

  getReunionBySerieReunion(codsreunion: number): Observable<any> {
    return this.http.get<Reunion>(`${this.baseUrl}` + 'reuniones/' + codsreunion);
  }

  /** Método que recoge la reunión por su código desde el Backend */

  getReunionByCodReunion(codreunion: number): Observable<Reunion> {
    return this.http.get<Reunion>(`${this.baseUrl}` + 'reunionbycodreunion/' + codreunion);
  }

  /** Método que recoge todas las reuniones desde el Backend */

  getReuniones(codusu: number, codsreunion: number): Observable<any> {
    return this.http.get<Reunion>(`${this.baseUrl}` + 'reuniones/' + codusu + '/' + codsreunion);
  }

  /** Método para crear nuevas reuniones */

  crearReunion(reunion: Reunion, codsreunion: number, codsusu: number[]): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'addnewreunion/' + codsreunion + '/' + codsusu, reunion);
  }

  /** Método para modificar una reunión */

  modificarReunion(reunion: Reunion, codusu: number[]): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'modificarreunion/' + codusu, reunion);
  }

  /** Método para eliminar participantes de una reunión */

  eliminarParticipante(reunion: Reunion, codusu: number): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'eliminarparticipante/' + codusu, reunion);
  }

}
