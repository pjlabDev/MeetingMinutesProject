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

  crearTareas(tarea: Tareas, codreunion: number, codusu: number[]): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'creartarea/' + codreunion + '/' + codusu, tarea);
  }

  getAllTareas(): Observable<any> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'gettareas');
  }

}