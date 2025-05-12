// dashboard.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { EquipementComponent } from './equipement/equipement.component';
import { TechnicienComponent } from './technicien/technicien.component';
import { LayoutsComponent } from './layouts/layouts.component';
import { HomeComponent } from './home/home.component';
import { InterventionComponent } from './intervention/intervention.component';
import { PanneComponent } from './panne/panne.component';
@NgModule({
  declarations: [
    EquipementComponent,
    LayoutsComponent,
    HomeComponent,
    TechnicienComponent,
    InterventionComponent,
    PanneComponent
  ],
  imports: [
    CommonModule,
    FormsModule, // Make sure this is here
    DashboardRoutingModule
  ]
})
export class DashboardModule { }