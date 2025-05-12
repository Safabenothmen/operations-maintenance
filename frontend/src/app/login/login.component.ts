import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  email: string = '';
  motDePasse: string = '';
  message: string = '';
  role?: string;  // Rôle de l'utilisateur (utilisé seulement pour gestion interne)
  nom?: string;   // Nom de l'utilisateur (utilisé seulement pour gestion interne)
  UserId?: number;  // ID de l'utilisateur (utilisé seulement pour gestion interne)

  constructor(private authService: AuthService, private router: Router) {}

  login() {
  this.authService.login(this.email, this.motDePasse).subscribe({
    next: (response) => {
      console.log('Réponse de connexion:', response); // Log pour vérifier la réponse
      const { role, nom, UserId } = response;

      localStorage.setItem('role', role);
      localStorage.setItem('nom', nom);
      localStorage.setItem('UserId', UserId.toString());

      // Rediriger vers le tableau de bord après la connexion réussie
      this.router.navigate(['/dashboard']);
    },
    error: (err) => {
      console.log('Erreur de connexion:', err);  // Log pour vérifier l'erreur
      this.message = 'Email ou mot de passe incorrect';
    }
  });
}

}
