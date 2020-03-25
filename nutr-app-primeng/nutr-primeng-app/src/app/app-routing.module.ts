import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', loadChildren:() => import('./shared/navbar/navbar.module').then(m => m.NavbarModule) },
    { path: 'calculator', loadChildren:() => import('./calculator/calculator.module').then(m => m.CalculatorModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
