import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InterventionService {
  private baseUrl = 'http://localhost:8080/intervention';

  constructor(private http: HttpClient) {}

  // ✅ Ajouter une intervention
  ajouterIntervention(equipementId: number, technicienId: number, intervention: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/ajouter/${equipementId}/${technicienId}`, intervention);
  }

  // ✅ Obtenir les interventions par technicien
  getInterventionsByTechnicien(technicienId: number): Observable<any[]> {
    console.log('Appel API pour les interventions du technicien avec ID:', technicienId);
    return this.http.get<any[]>(`${this.baseUrl}/technicien/${technicienId}`).pipe(
      tap(data => console.log('Réponse des interventions:', data))
    );
  }

  // ✅ Modifier une intervention
  modifierIntervention(interventionId: number, intervention: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/modifier/${interventionId}`, intervention).pipe(
      tap(data => console.log('Intervention modifiée:', data)),
      catchError(error => {
        console.error('Erreur lors de la modification:', error);
        throw error; // Optionnel : gérer l'erreur ici
      })
    );
  }
    // Modifier le statut d'une intervention

  modifierStatut(interventionId: number, statut: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/${interventionId}/statut/${statut}`, {}).pipe(
      tap(data => console.log('Statut modifié:', data)),
      catchError(error => {
        console.error('Erreur lors de la modification du statut:', error);
        throw error;
      })
    );
  }

  // ✅ Supprimer une intervention
  supprimerIntervention(interventionId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/supprimer/${interventionId}`);
  }
   getAllInterventions(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/all`).pipe(
      tap(data => console.log('Toutes les interventions:', data))
    );
  }
}
