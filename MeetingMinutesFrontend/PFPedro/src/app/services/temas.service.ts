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


  getTemasBySerieReunion(codsreunion: number): Observable<any> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'tema/' + codsreunion);
  }

  crearTemas(tema: Temas, codsreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'creartema/' + codsreunion, tema);
  }

}
