import { Component, OnInit } from '@angular/core';
import { InterventionService } from '../../services/intervention.service';
import { EquipementService } from '../../services/equipement.service';
import { TechnicienService } from '../../services/technicien.service';

@Component({
  standalone:false,
  selector: 'app-intervention',
  templateUrl: './intervention.component.html',
  styleUrls: ['./intervention.component.css']
})
export class InterventionComponent implements OnInit {
  intervention = {
    date: '',
    cout: 0,
    statut: 'Planifiee'
  };

  equipementId: number = 0;
  technicienId: number = 0;
  role: string = '';
  equipements: any[] = [];
  techniciens: any[] = [];
  interventions: any[] = [];
  affichage: 'ajouter' | 'liste' = 'liste';
  interventionId: number | null = null;

  constructor(
    private interventionService: InterventionService,
    private equipementService: EquipementService,
    private technicienService: TechnicienService
  ) {}

  ngOnInit(): void {
    const technicienId = localStorage.getItem('UserId');
    const role = localStorage.getItem('role');

    if (technicienId && role) {
      this.technicienId = +technicienId;
      this.role = role;

      if (role === 'TECHNICIEN') {
        this.getInterventionsForTechnician();
      } else if (role === 'ADMIN') {
        this.getAllInterventions();
      }
    }

    this.equipementService.getAllEquipement().subscribe(data => this.equipements = data);
    this.technicienService.getAllTechniciens().subscribe(data => this.techniciens = data);
  }

  // ✅ Récupérer toutes les interventions (ADMIN)
  getAllInterventions(): void {
    this.interventionService.getAllInterventions().subscribe({
      next: (data) => this.interventions = data,
      error: (err) => console.error('Erreur récupération interventions (admin)', err)
    });
  }

  // ✅ Récupérer les interventions du technicien connecté
  getInterventionsForTechnician(): void {
    this.interventionService.getInterventionsByTechnicien(this.technicienId).subscribe({
      next: (data) => this.interventions = data,
      error: (err) => console.error('Erreur récupération interventions (tech)', err)
    });
  }

  // ✅ Ajouter ou modifier
  onSubmit(): void {
    if (this.interventionId) {
      this.modifierIntervention();
    } else {
      this.ajouterIntervention();
    }
  }

  // ✅ Ajouter une intervention
  ajouterIntervention(): void {
    this.interventionService.ajouterIntervention(this.equipementId, this.technicienId, this.intervention).subscribe({
      next: () => {
        console.log('Intervention ajoutée');
        this.resetForm();
        this.refreshInterventions();
      },
      error: (err) => console.error('Erreur ajout', err)
    });
  }

  // ✅ Modifier une intervention
  modifierIntervention(): void {
    if (this.interventionId !== null) {
      this.interventionService.modifierIntervention(this.interventionId, this.intervention).subscribe({
        next: () => {
          console.log('Intervention modifiée');
          this.resetForm();
          this.refreshInterventions();
        },
        error: (err) => console.error('Erreur modification', err)
      });
    }
  }

  // ✅ Supprimer une intervention
  supprimerIntervention(id: number): void {
    this.interventionService.supprimerIntervention(id).subscribe({
      next: () => {
        console.log('Intervention supprimée');
        this.refreshInterventions();
      },
      error: (err) => console.error('Erreur suppression', err)
    });
  }

  // ✅ Charger une intervention à modifier (sans condition de rôle)
  loadInterventionToModify(id: number, intervention: any): void {
    // Pas de condition de rôle ici, donc on peut charger et modifier sans restriction.
    this.interventionId = id;
    this.intervention = {
      date: intervention.date,
      cout: intervention.cout,
      statut: intervention.statut
    };
    this.equipementId = intervention.equipement.id;
    this.technicienId = intervention.technicien.id;
    this.affichage = 'ajouter';
  }

  // ✅ Réinitialiser le formulaire
  resetForm(): void {
    this.intervention = { date: '', cout: 0, statut: 'Planifiee' };
    this.equipementId = 0;
    this.technicienId = 0;
    this.interventionId = null;
    this.affichage = 'liste';
  }

  // ✅ Actualiser les interventions
  refreshInterventions(): void {
    if (this.role === 'TECHNICIEN') {
      this.getInterventionsForTechnician();
    } else {
      this.getAllInterventions();
    }
  }
}
