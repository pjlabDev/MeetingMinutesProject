import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tareas } from '../clases/tareas';

@Injectable({
  providedIn: 'root'
})
export class TareasService {

  private baseUrl = 'http://localhost:8080/tareas/';

  constructor(private http: HttpClient) { }


  getTareasByCodReunion(codreunion: number): Observable<any> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'tareasreunion/' + codreunion);
  }

  crearTareas(tarea: Tareas, codreunion: number, codusu: number[], codsreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'creartarea/' + codreunion + '/' + codusu + '/' + codsreunion, tarea);
  }

  getAllTareasByCodSReunion(codsreunion: number): Observable<any> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'gettareas/' + codsreunion);
  }

  cerrarTareas(tarea: Tareas): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'cerrartareas', tarea);
  }

  getTareasByCodReunionAntiguaAndNoCerrada(codreunion: number, codsreunion: number): Observable<any> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'tareasreuantiguanocerr/' + codreunion + '/' + codsreunion);
  }

  saveTareasAntiguas(tareas: Tareas[], codreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'savetareasantiguas/' + codreunion, tareas);
  }

  getTareaByCodTarea(codtarea: number): Observable<Tareas> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'gettarea/' + codtarea);
  }

  modificarTarea(tarea: Tareas, codusu: number[]): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'modificartarea/' + codusu, tarea);
  }

}
