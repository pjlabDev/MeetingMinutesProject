import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Temas } from '../clases/temas';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TemasService {

  private baseUrl = 'https://mm-project.herokuapp.com/temas/';

  constructor(private http: HttpClient) { }

  /** Métodop para recoger los temas por código de Reunion desde el backend */

  getTemasByCodReunion(codreunion: number): Observable<any> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'tema/' + codreunion);
  }

  /** Método para recoger todos los temas por Serie de reunion desde el backend */

  getAllTemasByCodSReunion(codsreunion: number): Observable<any> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'alltemas/' + codsreunion);
  }

  /** Método para crear un nuevo tema */

  crearTemas(tema: Temas, codreunion: number, codsreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'creartema/' + codreunion + '/' + codsreunion, tema);
  }

  /** Método para añadir información al tema */

  añadirInfoTema(tema: Temas, codTema: number): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'addinfo/' + codTema, tema);
  }

  /** Método para añadir una decisión al tema */

  añadirDecisionTemas(tema: Temas, codTema: number): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'adddecision/' + codTema, tema);
  }

  /** Método para cerrar un tema */

  cerrarTemas(tema: Temas): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'cerrartema/', tema);
  }

  /** Método para recoger los temas de reuniones antiguas a la actual */

  getTemasByCodReunionAntiguaAndNoCerrado(codreunion: number, codsreunion: number): Observable<any> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'temasreuantnocerr/' + codreunion + '/' + codsreunion);
  }

  /** Método para añadir el tema antiguo a la reunión en la que estamos actualmente */

  saveTemaAntiguo(tema: Temas[], codreunion: number): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'savetemasant/' + codreunion, tema);
  }

  /** Método para recoger un tema por su código desde el backend */

  getTemaByCodTema(codtema: number): Observable<Temas> {
    return this.http.get<Temas>(`${this.baseUrl}` + 'gettema/' + codtema);
  }

  /** Método para modificar un tema */

  modificarTema(tema: Temas): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'modificarTema/', tema);
  }

  /** Método para añadir un seguimiento a un tema cerrado */

  addSeguimientoTemaCerrado(tema: Temas): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'addseguimiento', tema);
  }

}
