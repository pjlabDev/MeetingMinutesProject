import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Temas } from '../clases/temas';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private baseUrl = 'https://mm-project.herokuapp.com/email/';

  constructor(private http: HttpClient) { }

  /** Método para enviar la reunión a los participantes */

  enviarAgenda(receptores: any[], fechaReunion: string, temas: Temas[]): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'enviar/' + receptores + '/' + fechaReunion, temas);
  }

  /** Método para enviar el Acta a los asistenes */

  enviarActa(receptores: any[], fechaActa: string, temas: any[], tareas: any[], conclusion: string): Observable<any> {
    return this.http.get(`${this.baseUrl}` + 'enviaracta/' + fechaActa + '/' + receptores + '/' + temas + '/' + tareas + '/' + conclusion);
  }

  /** Método para enviar las tareas a los responsables */

  enviarTarea(codtarea: number): Observable<any> {
    return this.http.get(`${this.baseUrl}` + 'enviartarea/' + codtarea);
  }

  /** Método para enviar el comentario del Contáctanos a mi gmail */

  enviarComentario(nombre: string, correo: string, comentario: string): Observable<any> {
    return this.http.get(`${this.baseUrl}` + 'enviarcomentario/' + nombre + '/' + correo + '/' + comentario);
  }

}
