import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Employe {
  id?: number;
  nom: string;
  email: string;
  motDePasse: string;
dateEmbauche: Date; // ✅ Camel case
 
}
@Injectable({
  providedIn: 'root'
})



export class EmployeService {
    private baseUrl = 'http://localhost:8080'; // change si ton backend est ailleurs


  constructor(private http: HttpClient) { }
  ajouterEmploye(employe: Employe): Observable<Employe> {
      return this.http.post<Employe>(`${this.baseUrl}/employe/ajout`, employe);
    }

     getAllEmployes(): Observable<Employe[]> {
        return this.http.get<Employe[]>(`${this.baseUrl}/employe`);
      }

}
