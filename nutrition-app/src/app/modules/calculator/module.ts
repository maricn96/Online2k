import { CalculationDetailsComponent } from './components/calculator/calculation-details/calculation-details.component';
import { CalculationsComponent } from './components/calculator/calculations/calculations.component';
import { AppMaterialModule } from './../../shared/app-material/app-material.module';
import { CalculatorRoutingModule } from './routing.module';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { FoodService } from './services/food.service';
import { CalculatorComponent } from './components/calculator/calculator.component';

import { NgModule } from '@angular/core';
import { NewFoodComponent } from './components/new-food/new-food.component';

@NgModule({
  declarations: [
    NewFoodComponent,
    CalculatorComponent,
    CalculationsComponent,
    CalculationDetailsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    AppMaterialModule,
    CalculatorRoutingModule
  ],
  providers: [
    FoodService
  ],
  entryComponents: [
    //za dinamicki load ide ovde
    CalculationDetailsComponent
  ],
  exports: [
    CalculatorComponent,
    NewFoodComponent,
    CalculationsComponent
  ]
})
export class CalculatorModule { }
