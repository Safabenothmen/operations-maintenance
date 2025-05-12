import { Component, OnInit } from '@angular/core';
import { TechnicienService, Technicien } from '../../services/technicien.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
      standalone: false,

  selector: 'app-technicien',
  templateUrl: './technicien.component.html',
  styleUrls: ['./technicien.component.css']
})
export class TechnicienComponent implements OnInit {

  techniciens: Technicien[] = [];
  newTechnicien: Technicien = { nom: '', email: '', mdp: '', competences: '', disponibilite: true };
  loading: boolean = false;
  errorMessage: string = '';

  affichage: 'ajouter' | 'liste' = 'ajouter'; // Par défaut on affiche le formulaire

  constructor(private technicienService: TechnicienService) {}

  ngOnInit(): void {
    this.getAllTechniciens();
  }

  getAllTechniciens(): void {
    this.loading = true;
    this.technicienService.getAllTechniciens().subscribe(
      (data) => {
        this.techniciens = data;
        this.loading = false;
      },
      (error) => {
        this.errorMessage = 'Erreur lors de la récupération des techniciens';
        console.error(error);
        this.loading = false;
      }
    );
  }

  ajouterTechnicien(): void {
    this.technicienService.ajouterTechnicien(this.newTechnicien).subscribe(
      (data) => {
        this.techniciens.push(data);
        this.newTechnicien = { nom: '', email: '', mdp: '', competences: '', disponibilite: true };
        this.affichage = 'liste'; // Rediriger vers la liste après ajout
      },
      (error) => {
        this.errorMessage = 'Erreur lors de l\'ajout du technicien';
        console.error(error);
      }
    );
  }

  supprimerTechnicien(id: number): void {
    this.technicienService.supprimerTechnicien(id).subscribe(
      () => {
        this.techniciens = this.techniciens.filter(t => t.id !== id);
      },
      (error) => {
        this.errorMessage = 'Erreur lors de la suppression du technicien';
        console.error(error);
      }
    );
  }

  modifierTechnicien(id: number): void {
    this.technicienService.modifierTechnicien(id, this.newTechnicien).subscribe(
      (data) => {
        const index = this.techniciens.findIndex(t => t.id === id);
        if (index !== -1) this.techniciens[index] = data;
      },
      (error) => {
        this.errorMessage = 'Erreur lors de la mise à jour du technicien';
        console.error(error);
      }
    );
  }
}
