import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../clases/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private baseUrl = 'http://localhost:8080/';

  user: Usuario = new Usuario();
  usuario: Usuario = new Usuario();

  constructor(private http: HttpClient) { }


  loginUsuario(nombre: string, clave: string): Observable<Usuario> {

    this.http.get<Usuario>(`${this.baseUrl}` + 'login/' + nombre + '/' + clave)
    .subscribe(data => {
      this.user = data;
      if (this.user !== null) {
        sessionStorage.setItem('usuario', JSON.stringify(this.user));
      }
    },
       error => {
         console.log('Error al recibir el usuario.', error);
      });

    return this.http.get<Usuario>(`${this.baseUrl}` + 'login/' + nombre + '/' + clave);
  }

  isUserLoggedIn() {
    const usuario = sessionStorage.getItem('usuario');
    return !(usuario === null);
  }

  logOut() {
    sessionStorage.removeItem('usuario');
  }

  addNewUsuario(user: Usuario): Observable<any> {
      return this.http.post(`${this.baseUrl}addusuario`, user);
  }

  getAllUsuarios(): Observable<any> {
      return this.http.get<Usuario>(`${this.baseUrl}` + 'getusuarios');
  }

  getUsuariosNotInSerieReunion(codsreunion: number): Observable<any> {
      return this.http.get<Usuario>(`${this.baseUrl}` + 'usuariosnotinseriereunion/' + codsreunion);
  }

  getUsuariosInSerieReunion(codsreunion: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'usuariosinseriereunion/' + codsreunion);
  }

  getUsuariosByCodReunion(codreunion: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'usuariosbyreunion/' + codreunion);
  }

  getResponsablesTareas(codtarea: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'responsables/' + codtarea);
  }

  getUsuariosByCodUsu(codusu: number[]): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'userbycodusu/' + codusu);
  }

  isUserAdmin() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    if (this.usuario.rol === 'ADMINISTRADOR') {
      return true;
    } else {
      return false;
    }
  }

  isUserAdminAndJefe() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    if (this.usuario.rol === 'ADMINISTRADOR' || this.usuario.rol === 'JEFEREUNION') {
      return true;
    } else {
      return false;
    }
  }

  getUsuariosNotInTarea(codsreunion: number, codtarea: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'getusuariosnotintarea/' + codsreunion + '/' + codtarea);
  }

  getUsuariosNotInReunion(codreunion: number, codsreunion: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'getusuariosnotinreunion/' + codreunion + '/' + codsreunion);
  }

}
