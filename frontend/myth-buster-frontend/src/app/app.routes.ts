import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login';
import { RegisterComponent } from './components/auth/register/register';
import { Home } from './components/home/home';
import { AllMyths } from './components/all-myths/all-myths';
import { AboutComponent } from './components/about/about';
import { authGuard } from './guards/auth.guard';
import { MainLayoutComponent } from './components/layouts/main-layout.component';

export const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: '',
    component: MainLayoutComponent,
    canActivate: [authGuard],
    children: [
      { 
        path: 'ask', 
        component: Home
      },
      { 
        path: 'all-myths', 
        component: AllMyths
      },
      { 
        path: 'about', 
        component: AboutComponent
      }
    ]
  },
  // Catch all route - redirect to login
  { path: '**', redirectTo: '/login' }
];
