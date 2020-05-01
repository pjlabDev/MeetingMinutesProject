import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Temas } from '../clases/temas';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private baseUrl = 'http://localhost:8080/email/';

  constructor(private http: HttpClient) { }


  enviarAgenda(receptores: any[], fechaReunion: string, temas: Temas[], temasAntiguos: any[]): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'enviar/' + temasAntiguos + '/' + receptores + '/' + fechaReunion, temas);
  }






}
