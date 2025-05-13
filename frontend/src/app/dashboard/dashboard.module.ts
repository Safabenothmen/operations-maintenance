// dashboard.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { NgChartsModule } from 'ng2-charts';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { EquipementComponent } from './equipement/equipement.component';
import { TechnicienComponent } from './technicien/technicien.component';
import { LayoutsComponent } from './layouts/layouts.component';
import { HomeComponent } from './home/home.component';
import { InterventionComponent } from './intervention/intervention.component';
import { PanneComponent } from './panne/panne.component';
import { EmployeComponent } from './employe/employe.component';
import { InterventionTechComponent } from './intervention-tech/intervention-tech.component';
@NgModule({
  declarations: [
    EquipementComponent,
    LayoutsComponent,
    HomeComponent,
    TechnicienComponent,
    InterventionComponent,
    PanneComponent,
    EmployeComponent,
    InterventionTechComponent
  ],
  imports: [
    CommonModule,
    FormsModule, // Make sure this is here
    DashboardRoutingModule,
        MatCardModule,
       NgChartsModule

  ]
})
export class DashboardModule { }