import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { AllMyths } from './components/all-myths/all-myths';

export const routes: Routes = [
    { path: '', component: Home },
    { path: 'allMyths', component: AllMyths },
];
