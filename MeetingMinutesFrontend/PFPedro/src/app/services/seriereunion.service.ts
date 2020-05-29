import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SerieReunion } from '../clases/serie-reunion';

@Injectable({
  providedIn: 'root'
})
export class SeriereunionService {

  private baseUrl = 'http://localhost:8080/serie/';

  constructor(private http: HttpClient) { }


  getSerieReunionByUsuario(codusu: number): Observable<any> {
      return this.http.get<SerieReunion>(`${this.baseUrl}` + 'seriereuniones/' + codusu);
  }

  crearSerieReunion(reunion: SerieReunion, codsusu: number[]): Observable<any> {
      return this.http.post(`${this.baseUrl}` + 'addseriereunion/' + codsusu, reunion);
  }

  getSerieReunionByCodSReunion(codsreunion: number): Observable<SerieReunion> {
    return this.http.get<SerieReunion>(`${this.baseUrl}` + 'sereunion/' + codsreunion);
  }

  modifSerieReunion(reunion: SerieReunion): Observable<any> {
      return this.http.put(`${this.baseUrl}` + 'modifseriereunion/', reunion);
  }

  modifSerieReunionConInvitado(reunion: SerieReunion, codusu: number[]): Observable<any> {
      return this.http.put(`${this.baseUrl}` + 'modifseriereunionconinvitado/' + codusu, reunion);
  }

  eliminarParticipante(sr: SerieReunion, codusu: number): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'eliminarparticipante/' + codusu, sr);
  }


}
