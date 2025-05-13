import { Component, OnInit } from '@angular/core';
import { InterventionService } from './../../services/intervention.service';

@Component({
  selector: 'app-intervention-tech',
  standalone: false,
  templateUrl: './intervention-tech.component.html',
  styleUrls: ['./intervention-tech.component.css']
})
export class InterventionTechComponent implements OnInit {
  interventions: any[] = [];
  technicienId: number = 0;

  selectedIntervention: any = null;
  newStatus: string = '';

  constructor(private interventionService: InterventionService) {}

  ngOnInit(): void {
    const userId = localStorage.getItem('UserId');
    if (userId) {
      this.technicienId = +userId;
      this.getInterventions();
    } else {
      console.log('Aucun technicien connecté.');
    }
  }

  getInterventions(): void {
    this.interventionService.getInterventionsByTechnicien(this.technicienId).subscribe(
      (data) => {
        this.interventions = data;
        console.log('Interventions récupérées:', this.interventions);
      },
      (error) => {
        console.error('Erreur lors de la récupération des interventions:', error);
      }
    );
  }

  selectIntervention(intervention: any): void {
    this.selectedIntervention = intervention;
    this.newStatus = intervention.statut;
  }

  updateStatus(): void {
    if (this.selectedIntervention && this.newStatus) {
      // Appel à la méthode du service pour modifier le statut
      this.interventionService.modifierStatut(this.selectedIntervention.id, this.newStatus)
        .subscribe(
          response => {
            console.log('Statut mis à jour :', response);
            this.selectedIntervention = null;
            this.getInterventions(); // Récupérer à nouveau les interventions après mise à jour
          },
          error => {
            console.error('Erreur lors de la mise à jour du statut :', error);
          }
        );
    }
  }
}
