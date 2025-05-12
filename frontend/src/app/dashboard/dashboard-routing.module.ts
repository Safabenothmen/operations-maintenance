import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutsComponent } from './layouts/layouts.component';
import { HomeComponent } from './home/home.component';
import { TechnicienComponent } from './technicien/technicien.component';
import { EquipementComponent } from './equipement/equipement.component';
import { PanneComponent } from './panne/panne.component';
import { InterventionComponent } from './intervention/intervention.component';
const routes: Routes = [
  {
    path: '',
    component: LayoutsComponent,
    children: [
      { path: '', component: HomeComponent },
        { path: 'technicien', component: TechnicienComponent } ,// Route pour Technicien
       { path: 'equipement', component: EquipementComponent }, // Route pour Technicien
              { path: 'intervention', component: InterventionComponent }, // Route pour Technicien
              { path: 'panne', component: PanneComponent } // Route pour Technicien



    ],

  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
