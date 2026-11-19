import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Home } from './home/home';
import { authGuard } from './auth-guard';
import { Register } from './register/register';
import { Users } from './users/users';
import { Matches } from './matches/matches';

export const routes: Routes = [
  {
    path: 'login',
    component: Login
  },
  {
    path: 'home',
    component: Home,
    canActivate: [authGuard]
  },
  {
    path: 'register',
    component: Register
  },
  {
    path: 'users',
    component: Users,
    canActivate: [authGuard]
  },
  {
    path: 'matches',
    component: Matches,
    canActivate: [authGuard]
  }
];