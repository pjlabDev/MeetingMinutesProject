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

  cerrarSerieReunion(codsreunion: number): Observable<any> {
      return this.http.get<SerieReunion>(`${this.baseUrl}` + 'cerrar/' + codsreunion);
  }


}
