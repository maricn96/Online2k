import { ButtonModule, RadioButtonModule, MenubarModule } from 'primeng/primeng';
import { CalculatorRoutingModule } from './calculator-routing.module';
import { CalculatorComponent } from './calculator.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarModule } from '../shared/navbar/navbar.module';

@NgModule({
	imports: [
		CommonModule,
		CalculatorRoutingModule,
		ButtonModule,
		RadioButtonModule,
		MenubarModule,
		NavbarModule
	],
	declarations: [
		CalculatorComponent
	],
	exports: [
	],
	providers: []
})
export class CalculatorModule { }