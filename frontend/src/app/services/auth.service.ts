import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Créez une interface pour la réponse
interface LoginResponse {
  role: string;
  nom: string;
  message: string;
    UserId: number;  // Ajoutez ici la propriété UserId

}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/auth/login'; // adapt to your backend URL

  constructor(private http: HttpClient) {}

  // Modifiez le type de retour en 'LoginResponse'
  login(email: string, motDePasse: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.apiUrl, { email, motDePasse });
  }
}
