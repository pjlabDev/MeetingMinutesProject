import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SerieReunion } from '../clases/serie-reunion';

@Injectable({
  providedIn: 'root'
})
export class SeriereunionService {

  private baseUrl = 'https://mm-project.herokuapp.com/serie/';

  constructor(private http: HttpClient) { }

  /** Método que recoge las Serie de reuniones por usuario desde el backend */

  getSerieReunionByUsuario(codusu: number): Observable<any> {
      return this.http.get<SerieReunion>(`${this.baseUrl}` + 'seriereuniones/' + codusu);
  }

  /** Método para crear una nueva Serie de reuniones desde el Backend */

  crearSerieReunion(reunion: SerieReunion, codsusu: number[]): Observable<any> {
      return this.http.post(`${this.baseUrl}` + 'addseriereunion/' + codsusu, reunion);
  }

  /** Método para recoger una Serie de Reuniones por su código desde el Backend */

  getSerieReunionByCodSReunion(codsreunion: number): Observable<SerieReunion> {
    return this.http.get<SerieReunion>(`${this.baseUrl}` + 'sereunion/' + codsreunion);
  }

  /** Método para modificar una Serie de Reuniones */

  modifSerieReunion(reunion: SerieReunion): Observable<any> {
      return this.http.put(`${this.baseUrl}` + 'modifseriereunion/', reunion);
  }

  /** Método para modificar una Serie de Reuniones con más invitados */

  modifSerieReunionConInvitado(reunion: SerieReunion, codusu: number[]): Observable<any> {
      return this.http.put(`${this.baseUrl}` + 'modifseriereunionconinvitado/' + codusu, reunion);
  }

  /** Método para eliminar participantes de la Serie de reunión */

  eliminarParticipante(sr: SerieReunion, codusu: number): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'eliminarparticipante/' + codusu, sr);
  }


}
