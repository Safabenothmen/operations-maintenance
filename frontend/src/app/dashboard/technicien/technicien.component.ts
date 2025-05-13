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
  newTechnicien: Technicien = { nom: '', email: '', motDePasse: '', disponibilite: true, competences: '' };
  editMode: boolean = false;
  editedTechnicienId: number | null = null;

  constructor(private technicienService: TechnicienService) {}

  ngOnInit(): void {
    this.getAllTechniciens();
  }

  getAllTechniciens(): void {
    this.technicienService.getAllTechniciens().subscribe(data => {
      this.techniciens = data;
    });
  }

  ajouterOuModifierTechnicien(): void {
    if (this.editMode && this.editedTechnicienId !== null) {
      this.technicienService.modifierTechnicien(this.editedTechnicienId, this.newTechnicien).subscribe(updated => {
        const index = this.techniciens.findIndex(t => t.id === this.editedTechnicienId);
        if (index !== -1) this.techniciens[index] = updated;
        this.resetForm();
      });
    } else {
      this.technicienService.ajouterTechnicien(this.newTechnicien).subscribe(created => {
        this.techniciens.push(created);
        this.resetForm();
      });
    }
  }

  chargerPourModification(technicien: Technicien): void {
    this.newTechnicien = { ...technicien };
    this.editedTechnicienId = technicien.id!;
    this.editMode = true;
  }

  supprimerTechnicien(id: number): void {
    this.technicienService.supprimerTechnicien(id).subscribe(() => {
      this.techniciens = this.techniciens.filter(t => t.id !== id);
    });
  }

  resetForm(): void {
    this.newTechnicien = { nom: '', email: '', motDePasse: '', disponibilite: true, competences: '' };
    this.editMode = false;
    this.editedTechnicienId = null;
  }
}