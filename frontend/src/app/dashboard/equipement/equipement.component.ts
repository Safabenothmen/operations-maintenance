import { Component, OnInit } from '@angular/core';
import { EquipementService } from '../../services/equipement.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
    standalone: false,
//imports: [CommonModule, FormsModule],

  selector: 'app-equipement',
  templateUrl: './equipement.component.html',
  styleUrls: ['./equipement.component.css']
})
export class EquipementComponent implements OnInit {
  equipement = {
    nom: '',
    etat: 'Fonctionnel'
  };
  equipements: any[] = [];
  isFormVisible = true; // Contrôler l'affichage entre le formulaire et le tableau

  constructor(private equipementService: EquipementService) {}

  ngOnInit(): void {
    this.getEquipements(); // Charger la liste des équipements au démarrage
  }

  // Méthode pour récupérer les équipements
  getEquipements(): void {
    this.equipementService.getAllEquipement().subscribe({
      next: (data) => {
        this.equipements = data;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des équipements', err);
      }
    });
  }

  // Soumettre un nouvel équipement
  onSubmit() {
    this.equipementService.ajouterEquipement(this.equipement).subscribe({
      next: (res) => {
        console.log('Équipement ajouté avec succès', res);
        this.equipement = { nom: '', etat: 'Fonctionnel' }; // reset form
        this.getEquipements(); // Rafraîchir la liste après l'ajout
      },
      error: (err) => {
        console.error('Erreur lors de l’ajout', err);
      }
    });
  }

  // Afficher le formulaire
  showForm() {
    this.isFormVisible = true;
  }

  // Afficher la liste des équipements
  showEquipments() {
    this.isFormVisible = false;
  }
}
