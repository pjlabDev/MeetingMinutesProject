import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Acta } from '../clases/acta';

@Injectable({
  providedIn: 'root'
})
export class ActaService {

  private baseUrl = 'http://localhost:8080/actas/';

  constructor(private http: HttpClient) { }


  generarActa(acta: Acta, codreunion: number, codusu: number[]): Observable<any> {
    return this.http.post(`${this.baseUrl}` + 'newacta/' + codreunion + '/' + codusu, acta);
  }


}
