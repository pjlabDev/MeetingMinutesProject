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

  getReuniones(codusu: number, codsreunion: number): Observable<any> {
    return this.http.get<Reunion>(`${this.baseUrl}` + 'reuniones/' + codusu + '/' + codsreunion);
  }

  crearReunion(reunion: Reunion, codsreunion: number, codsusu: number[]): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'addnewreunion/' + codsreunion + '/' + codsusu, reunion);
  }

  modificarReunion(reunion: Reunion, codusu: number[]): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'modificarreunion/' + codusu, reunion);
  }

}
