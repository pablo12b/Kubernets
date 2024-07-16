import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../enviroment/Cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) {}

  getClientes() {
    return this.http.get(`http://localhost:8080/demo63/rest/universidad/list`);
  }

  agregarCliente(cliente: Cliente) {
    return this.http.post(`http://localhost:8080/demo63/rest/universidad`, cliente);
  }

  actualizarCliente(cliente: Cliente) {
    return this.http.put(`http://localhost:8080/demo63/rest/universidad`, cliente);
  }

  borrarCliente(dni: String | undefined) {
    return this.http.delete(`http://localhost:8080/demo63/rest/universidad?codigo=` + dni);
  }
}
