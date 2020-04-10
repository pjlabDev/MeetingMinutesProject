import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reunion } from '../clases/reunion';

@Injectable({
  providedIn: 'root'
})
export class ReunionService {

  private baseUrl = 'http://localhost:8080/reunion/';

  constructor(private http: HttpClient) { }


  getReunionBySerieReunion(codsreunion: number): Observable<any> {
    return this.http.get<Reunion>(`${this.baseUrl}` + 'reuniones/' + codsreunion);
  }

  getReunionByCodReunion(codreunion: number): Observable<Reunion> {
    return this.http.get<Reunion>(`${this.baseUrl}` + 'reunionbycodreunion/' + codreunion);
  }

  getReunionByUsuarios(codusu: number): Observable<any> {
    return this.http.get<Reunion>(`${this.baseUrl}` + 'reunionbyusuario/' + codusu);
  }

  crearReunion(reunion: Reunion, codsreunion: number, codsusu: number[]): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'addnewreunion/' + codsreunion + '/' + codsusu, reunion);
  }

}
