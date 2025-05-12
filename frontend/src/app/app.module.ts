import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';  // Importer HttpClientModule
import { FormsModule } from '@angular/forms';  // Importer FormsModule ici

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PagenotfoundComponent
  ],
  imports: [
    BrowserModule,
        FormsModule,  // Assurez-vous que FormsModule est ici

    AppRoutingModule,
        HttpClientModule,  // Ajouter HttpClientModule ici

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
