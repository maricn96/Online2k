import { CalculatorComponent } from './calculator.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
	{ path: '', component: CalculatorComponent, children: [
		{ path: 'newFood', loadChildren: () => import('./new-food/new-food.module').then(m => m.NewFoodModule) },
		{ path: 'dashboard', loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule) }
	]},
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class CalculatorRoutingModule { }
