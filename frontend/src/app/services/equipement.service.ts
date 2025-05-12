import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EquipementService {
  private apiUrl = 'http://localhost:8080/equipement'; // à adapter si besoin

  constructor(private http: HttpClient) {}

  ajouterEquipement(equipement: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajout`, equipement);
  }

   getAllEquipement(): Observable<any> {
    return this.http.get(`${this.apiUrl}/all`);
  }
}
