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

  crearReunion(reunion: Reunion, codsreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'addreunion/' + codsreunion, reunion);
  }

  getReunionByCodReunion(codreunion: number): Observable<Reunion> {
    return this.http.get<Reunion>(`${this.baseUrl}` + 'reunionbycodreunion/' + codreunion);
  }


}
