import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Temas } from '../clases/temas';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TemasService {

  private baseUrl = 'http://localhost:8080/temas/';

  constructor(private http: HttpClient) { }


  getTemasByCodReunion(codreunion: number): Observable<any> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'tema/' + codreunion);
  }

  getAllTemasByCodSReunion(codsreunion: number): Observable<any> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'alltemas/' + codsreunion);
  }

  crearTemas(tema: Temas, codreunion: number, codsreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'creartema/' + codreunion + '/' + codsreunion, tema);
  }

  añadirInfoTema(tema: Temas, codTema: number): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'addinfo/' + codTema, tema);
  }

  añadirDecisionTemas(tema: Temas, codTema: number): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'adddecision/' + codTema, tema);
  }

  cerrarTemas(tema: Temas): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'cerrartema/', tema);
  }

  getTemasByCodReunionAntiguaAndNoCerrado(codreunion: number, codsreunion: number): Observable<any> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'temasreuantnocerr/' + codreunion + '/' + codsreunion);
  }

  saveTemaAntiguo(tema: Temas[], codreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'savetemasant/' + codreunion, tema);
  }

  getTemaByCodTema(codtema: number): Observable<Temas> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'gettema/' + codtema);
  }

  modificarTema(tema: Temas): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'modificarTema/', tema);
  }

  addSeguimientoTemaCerrado(tema: Temas): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'addseguimiento', tema);
  }

}
