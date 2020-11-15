import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthGuardService} from "./services/auth-guard.service";
import {LoginPage} from "./pages/login/login.page";

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./modules/home-page/home-page.module').then(m => m.HomePageModule),
    data: { public: true },
  },
  {
    path: 'admin-pages',
    loadChildren: () => import('./modules/admin-pages/admin-pages.module').then(m => m.AdminPagesModule),
    canActivate: [AuthGuardService]
  },
  {
    path: 'login',
    component: LoginPage
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
