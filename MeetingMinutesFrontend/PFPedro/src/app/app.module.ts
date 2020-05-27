import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';
import { AboutComponent } from './components/about/about.component';
import { NuevaseriereunionComponent } from './components/nuevaseriereunion/nuevaseriereunion.component';
import { SeriereunionComponent } from './components/seriereunion/seriereunion.component';
import { ModifSerieReunionComponent } from './components/modif-serie-reunion/modif-serie-reunion.component';
import { NuevareunionComponent } from './components/nuevareunion/nuevareunion.component';
import { TemasComponent } from './components/temas/temas.component';
import { ReunionComponent } from './components/reunion/reunion.component';
import { ActaComponent } from './components/acta/acta.component';
import { TareasComponent } from './components/tareas/tareas.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    AboutComponent,
    NuevaseriereunionComponent,
    SeriereunionComponent,
    ModifSerieReunionComponent,
    NuevareunionComponent,
    TemasComponent,
    ReunionComponent,
    ActaComponent,
    TareasComponent,
    UsuariosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    Ng2SearchPipeModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
