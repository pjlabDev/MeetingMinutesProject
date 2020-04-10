import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
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


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'acercade', component: AboutComponent},
  {path: 'addseriereunion', component: NuevaseriereunionComponent},
  {path: 'seriereunion/:id', component: SeriereunionComponent},
  {path: 'modifseriereunion/:id', component: ModifSerieReunionComponent},
  {path: 'addreunion/:id', component: NuevareunionComponent},
  {path: 'temas', component: TemasComponent},
  {path: 'reunion/:id/:idd', component: ReunionComponent},
  {path: 'acta/:id/:idd', component: ActaComponent},
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
