import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { InterventionComponent } from './dashboard/intervention/intervention.component';
import { TechnicienComponent } from './dashboard/technicien/technicien.component';
import { EquipementComponent } from './dashboard/equipement/equipement.component';
const routes: Routes = [
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./dashboard/dashboard.module').then((m) => m.DashboardModule),
    // canActivate: [AuthGuard],
  },
  { path: '', component: LoginComponent },
  




  //{ path: '**', component: PagenotfoundComponent },
];



@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
