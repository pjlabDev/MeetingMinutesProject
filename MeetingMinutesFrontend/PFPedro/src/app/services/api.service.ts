import { Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../clases/usuario';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl = 'http://localhost:8080/';

  user: Usuario = new Usuario();

  constructor(private http: HttpClient) { }


  loginUsuario(nombre: string, clave: string): Observable<Usuario> {

    this.http.get<Usuario>(`${this.baseUrl}` + 'login/' + nombre + '/' + clave)
    .subscribe(data => {
      console.log('data', data);
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

  getUsuariosNotInReunion(codsreunion: number): Observable<any> {
      return this.http.get<Usuario>(`${this.baseUrl}` + 'usuariosnotinreunion/' + codsreunion);
  }

  getUsuariosInReunion(codsreunion: number): Observable<any> {
    return this.http.get<Usuario>(`${this.baseUrl}` + 'usuariosinreunion/' + codsreunion);
}

}
