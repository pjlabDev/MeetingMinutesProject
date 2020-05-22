import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate } from '@angular/router';
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
import { AuthGuard } from './guards/auth.guard';
import { RolGuard } from './guards/rol.guard';
import { LetrasGuard } from './guards/letras.guard';
import { LetrastwoGuard } from './guards/letrastwo.guard';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'register', component: RegisterComponent, canActivate: [AuthGuard, RolGuard]},
  {path: 'acercade', component: AboutComponent},
  {path: 'addseriereunion', component: NuevaseriereunionComponent, canActivate: [AuthGuard, RolGuard]},
  {path: 'seriereunion/:id', component: SeriereunionComponent, canActivate: [AuthGuard, LetrasGuard]},
  {path: 'modifseriereunion/:id', component: ModifSerieReunionComponent, canActivate: [AuthGuard, RolGuard, LetrasGuard]},
  {path: 'addreunion/:id', component: NuevareunionComponent, canActivate: [AuthGuard, RolGuard, LetrasGuard]},
  {path: 'reunion/:id/:idd', component: ReunionComponent, canActivate: [AuthGuard, LetrastwoGuard]},
  {path: 'acta/:id/:idd', component: ActaComponent, canActivate: [AuthGuard, RolGuard, LetrastwoGuard]},
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
