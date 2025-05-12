import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';  // ← importer Router

@Component({
  selector: 'app-layouts',
  standalone: false,
  templateUrl: './layouts.component.html',
  styleUrls: ['./layouts.component.css']
})
export class LayoutsComponent implements OnInit {

  nomUtilisateur: string = '';
  roleUtilisateur: string = '';

  constructor(private router: Router) {}  // ← injecter le router

  ngOnInit(): void {
    const nom = localStorage.getItem('nom');
    const role = localStorage.getItem('role');

    if (nom) this.nomUtilisateur = nom;
    if (role) this.roleUtilisateur = role;
  }

  // 🔴 Méthode de déconnexion
  logout(): void {
    localStorage.clear(); // Supprimer toutes les infos de session
    this.router.navigate(['/']); // Rediriger vers login
  }
}
