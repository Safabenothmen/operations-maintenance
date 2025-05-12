import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PanneService {
  private apiUrl = 'http://localhost:8080/panne'; // adapte si nécessaire

  constructor(private http: HttpClient) {}

  ajouterPanne(equipementId: number, panne: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouter/${equipementId}`, panne);
  }

 getAllPannes(): Observable<any[]> {
  return this.http.get<any[]>('http://localhost:8080/panne/all');
}
  modifierPanne(id: number, panne: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/modifer/${id}`, panne);
  }

  supprimerPanne(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/supprimer/${id}`);
  }

}
