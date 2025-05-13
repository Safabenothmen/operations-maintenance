import { Component, OnInit } from '@angular/core';
import { EmployeService, Employe } from '../../services/employe.service';

@Component({
  selector: 'app-employe',
  standalone: false,
  templateUrl: './employe.component.html',
  styleUrls: ['./employe.component.css']
})
export class EmployeComponent implements OnInit {

  employes: Employe[] = [];
  newEmploye: Employe = { nom: '', email: '', motDePasse: '', dateEmbauche: new Date() };
  loading: boolean = false;
  errorMessage: string = '';
  affichage: 'ajouter' | 'liste' = 'ajouter';

  constructor(private employeService: EmployeService) {}

  ngOnInit(): void {
    this.getAllEmployes();
  }

  getAllEmployes(): void {
    this.loading = true;
    this.employeService.getAllEmployes().subscribe(
      (data) => {
        this.employes = data;
        this.loading = false;
      },
      (error) => {
        this.errorMessage = 'Erreur lors de la récupération des employés';
        console.error(error);
        this.loading = false;
      }
    );
  }

  ajouterEmploye(): void {
    this.employeService.ajouterEmploye(this.newEmploye).subscribe(
      (data) => {
        this.employes.push(data);
        this.newEmploye = { nom: '', email: '', motDePasse: '', dateEmbauche: new Date() };
        this.affichage = 'liste';
      },
      (error) => {
        this.errorMessage = 'Erreur lors de l\'ajout de l\'employé';
        console.error(error);
      }
    );
  }

  supprimerEmploye(id: number): void {
    // Si tu ajoutes un endpoint DELETE, implémente ceci :
    // this.employeService.supprimerEmploye(id).subscribe(() => {
    //   this.employes = this.employes.filter(e => e.id !== id);
    // }, error => {
    //   this.errorMessage = 'Erreur lors de la suppression';
    // });

    // Placeholder
    console.log('Suppression non encore implémentée');
  }

  modifierEmploye(id: number): void {
    // Pareil, ajouter un endpoint update dans le backend
    // this.employeService.modifierEmploye(id, this.newEmploye).subscribe(...)
    console.log('Modification non encore implémentée');
  }
}
