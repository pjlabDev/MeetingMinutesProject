import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../clases/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private baseUrl = 'https://mm-project.herokuapp.com/usuarios/';

  user: Usuario = new Usuario();
  usuario: Usuario = new Usuario();

  constructor(private http: HttpClient) { }

  /** Método para loguearse, guardando los datos de un usuario en el sessionStorage */

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

  /** Método para comprobar si un usuario está logueado */

  isUserLoggedIn() {
    const usuario = sessionStorage.getItem('usuario');
    return !(usuario === null);
  }

  /** Método para cerrar sesión en la página */

  logOut() {
    sessionStorage.removeItem('usuario');
  }

  /** Método para añadir un nuevo usuario */

  addNewUsuario(user: Usuario): Observable<any> {
      return this.http.post(`${this.baseUrl}addusuario`, user);
  }

  /** Método para recoger todos los usuarios desde el backend */

  getAllUsuarios(): Observable<any> {
      return this.http.get<Usuario>(`${this.baseUrl}` + 'getusuarios');
  }

  /** Método para recoger los usuarios que no estén en la Serie de Reunion con x código desde el backend */

  getUsuariosNotInSerieReunion(codsreunion: number): Observable<any> {
      return this.http.get<Usuario>(`${this.baseUrl}` + 'usuariosnotinseriereunion/' + codsreunion);
  }

  /** Método para recoger los usuarios que estén en la Serie de Reunion con x código desde el backend */

  getUsuariosInSerieReunion(codsreunion: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'usuariosinseriereunion/' + codsreunion);
  }

  /** Método para recoger los usuarios de una reunión con x código desde el backend */

  getUsuariosByCodReunion(codreunion: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'usuariosbyreunion/' + codreunion);
  }

  /** Método para recoger los responsables de una tarea con x código desde el backend */

  getResponsablesTareas(codtarea: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'responsables/' + codtarea);
  }

  /** Método para recoger datos de un usuario por su código */

  getUsuariosByCodUsu(codusu: number[]): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'userbycodusu/' + codusu);
  }

  /** Método para comprobar si un usuario es administrador */

  isUserAdmin() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    if (this.usuario.rol === 'ADMINISTRADOR') {
      return true;
    } else {
      return false;
    }
  }

  /** Método para comporbar si un usuario es administrador o jefe de reunión */

  isUserAdminAndJefe() {
    this.usuario = JSON.parse(sessionStorage.getItem('usuario'));
    if (this.usuario.rol === 'ADMINISTRADOR' || this.usuario.rol === 'JEFEREUNION') {
      return true;
    } else {
      return false;
    }
  }

  /** Método para recoger los usuarios que no estén en la tarea con código x desde el backend */

  getUsuariosNotInTarea(codsreunion: number, codtarea: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'getusuariosnotintarea/' + codsreunion + '/' + codtarea);
  }

  /** Método para recoger los usuarios que no estén en la reunión con código x desde el backend */

  getUsuariosNotInReunion(codreunion: number, codsreunion: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'getusuariosnotinreunion/' + codreunion + '/' + codsreunion);
  }

  /** Método para recoger los usuarios por código desde el backend */

  getUserByCodUsu(codusu: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'getuserbycod/' + codusu);
  }

  /** Método para modificar un usuario */

  modificarUsuario(user: Usuario): Observable<any> {
    return this.http.put(`${this.baseUrl}` + 'modificarusuario', user);
  }

  /** Método para eliminar un usuario */

  eliminarUsuario(codusu: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + 'eliminarusuario/' + codusu);
  }

}
