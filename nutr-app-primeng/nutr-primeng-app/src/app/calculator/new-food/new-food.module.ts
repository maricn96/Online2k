import { NewFoodComponent } from './new-food.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';

const routes: Routes = [
	{ path: '', component: NewFoodComponent },
];

@NgModule({
	imports: [
		CommonModule,
		FormsModule,
		ReactiveFormsModule,
		RouterModule.forChild(routes),
		InputTextModule,
		ButtonModule,
        
	],
	declarations: [
		NewFoodComponent
	],
	exports: [
	],
	providers: [
		DatePipe
	]
})
export class NewFoodModule { }