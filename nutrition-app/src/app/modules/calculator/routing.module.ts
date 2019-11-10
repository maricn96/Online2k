import { CalculatorComponent } from './components/calculator/calculator.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NewFoodComponent } from './components/new-food/new-food.component';
import { CalculationsComponent } from './components/calculator/calculations/calculations.component';
import { CalculationDetailsComponent } from './components/calculator/calculation-details/calculation-details.component';


const routes: Routes = [
    { path: 'calculator', component: CalculatorComponent },
    { path: 'addnew', component: NewFoodComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
    declarations: []
})
export class CalculatorRoutingModule { }
