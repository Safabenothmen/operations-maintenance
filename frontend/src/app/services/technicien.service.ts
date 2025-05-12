import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Technicien {
  id?: number;
  nom: string;
  email: string;
  mdp: string;
  competences: string;
  disponibilite: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class TechnicienService {

  private baseUrl = 'http://localhost:8080'; // change si ton backend est ailleurs

  constructor(private http: HttpClient) {}

  ajouterTechnicien(technicien: Technicien): Observable<Technicien> {
    return this.http.post<Technicien>(`${this.baseUrl}/technicien/ajout`, technicien);
  }



  getAllTechniciens(): Observable<Technicien[]> {
    return this.http.get<Technicien[]>(`${this.baseUrl}/techniciens`);
  }

  supprimerTechnicien(id: number): Observable<Technicien> {
    return this.http.delete<Technicien>(`${this.baseUrl}/supprimerTechnicien/${id}`);
  }

  modifierTechnicien(id: number, technicien: Technicien): Observable<Technicien> {
    return this.http.put<Technicien>(`${this.baseUrl}/Modifiertechnicien/${id}`, technicien);
  }

  getTechniciensDisponibles(): Observable<Technicien[]> {
    return this.http.get<Technicien[]>(`${this.baseUrl}/techniciens/disponibles`);
  }
}
