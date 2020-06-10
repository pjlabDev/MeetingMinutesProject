import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tareas } from '../clases/tareas';

@Injectable({
  providedIn: 'root'
})
export class TareasService {

  private baseUrl = 'https://mm-project.herokuapp.com/tareas/';

  constructor(private http: HttpClient) { }

  /** Método para recoger las tareas por código de Reunión desde el Backend */

  getTareasByCodReunion(codreunion: number): Observable<any> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'tareasreunion/' + codreunion);
  }

  /** Método para crear una nueva tarea */

  crearTareas(tarea: Tareas, codreunion: number, codusu: number[], codsreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'creartarea/' + codreunion + '/' + codusu + '/' + codsreunion, tarea);
  }

  /** Método para recoger todas las tareas por código de la Serie de reuniones desde el Backend */

  getAllTareasByCodSReunion(codsreunion: number): Observable<any> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'gettareas/' + codsreunion);
  }

  /** Método para cerrar tareas */

  cerrarTareas(tarea: Tareas): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'cerrartareas', tarea);
  }

  /** Método que recoge las tareas antiguas a la reunión a la que estemos actualmente desde el backend */

  getTareasByCodReunionAntiguaAndNoCerrada(codreunion: number, codsreunion: number): Observable<any> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'tareasreuantiguanocerr/' + codreunion + '/' + codsreunion);
  }

  /** Método para añadir la tarea antigua a la reunión actual */

  saveTareasAntiguas(tareas: Tareas[], codreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'savetareasantiguas/' + codreunion, tareas);
  }

  /** Método que recoge las tareas por su código desde el backend */

  getTareaByCodTarea(codtarea: number): Observable<Tareas> {
    return this.http.get<Tareas>(`${this.baseUrl}` + 'gettarea/' + codtarea);
  }

  /** Método para modificar una tarea */

  modificarTarea(tarea: Tareas, codusu: number[]): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'modificartarea/' + codusu, tarea);
  }

  /** Método para eliminar responsables de una tarea */

  eliminarResponsable(tarea: Tareas, codusu: number): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'eliminarresponsable/' + codusu, tarea);
  }

}
