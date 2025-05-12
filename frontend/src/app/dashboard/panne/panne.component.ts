import { Component, OnInit } from '@angular/core';
import { PanneService } from '../../services/panne.service';
import { EquipementService } from '../../services/equipement.service';

@Component({
        standalone: false,

  selector: 'app-panne',
  templateUrl: './panne.component.html',
  styleUrls: ['./panne.component.css']
})
export class PanneComponent implements OnInit {
  panne = {
    description: '',
    categorie: '',
    date_sign: ''
  };
  equipementId: number = 0;
  equipements: any[] = [];
  pannes: any[] = []; // Liste des pannes
  panneToEdit: any = null; // Pour éditer une panne

  affichage: string = 'liste'; // Contrôle l'affichage entre formulaire et liste

  constructor(
    private panneService: PanneService,
    private equipementService: EquipementService
  ) {}

  ngOnInit(): void {
    // Charger la liste des équipements et des pannes
    this.equipementService.getAllEquipement().subscribe({
      next: (data) => {
        this.equipements = data;
      },
      error: (err) => {
        console.error('Erreur de récupération des équipements', err);
      }
    });

    this.chargerPannes(); // Charger les pannes dès que le composant est initialisé
  }

  onSubmit() {
    if (this.panneToEdit) {
      // Si une panne est en mode édition, on la modifie
      this.modifierPanne();
    } else {
      // Si aucune panne n'est en édition, on ajoute une nouvelle panne
      this.panneService.ajouterPanne(this.equipementId, this.panne).subscribe({
        next: (res) => {
          console.log('Panne ajoutée avec succès', res);
          this.resetForm();
          this.chargerPannes();
        },
        error: (err) => {
          console.error('Erreur lors de l’ajout de la panne', err);
        }
      });
    }
  }

  chargerPannes() {
    this.panneService.getAllPannes().subscribe({
      next: (data) => {
        this.pannes = data;
      },
      error: (err) => {
        console.error('Erreur lors de la récupération des pannes', err);
      }
    });
  }

  // Modifier une panne
  modifierPanne() {
  // On s'assure de ne garder que l'id de l'équipement
  this.panneToEdit.equipement = { id: this.equipementId };

  this.panneService.modifierPanne(this.panneToEdit.id, this.panneToEdit).subscribe({
    next: (res) => {
      console.log('Panne modifiée avec succès', res);
      this.resetForm();
      this.chargerPannes();
    },
    error: (err) => {
      console.error('Erreur lors de la modification de la panne', err);
    }
  });
}


  // Supprimer une panne
  supprimerPanne(id: number) {
    this.panneService.supprimerPanne(id).subscribe({
      next: (res) => {
        console.log('Panne supprimée avec succès', res);
        this.chargerPannes();
      },
      error: (err) => {
        console.error('Erreur lors de la suppression de la panne', err);
      }
    });
  }

  // Remettre le formulaire à zéro après une action
  resetForm() {
    this.panne = { description: '', categorie: '', date_sign: '' };
    this.equipementId = 0;
    this.panneToEdit = null;
  }

  // Charger une panne pour modification
  chargerPannePourModification(panne: any) {
    this.panneToEdit = { ...panne }; // Copier les valeurs de la panne dans panneToEdit
    this.equipementId = panne.equipement?.id; // Pré-charger l'équipement
    this.affichage = 'ajouter'; // Passer en mode ajout pour l'édition
  }
}