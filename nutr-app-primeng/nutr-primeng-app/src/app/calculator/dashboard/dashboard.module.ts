import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard.component';
import { CountComponent } from './count/count.component';
import {InputTextModule} from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import {ButtonModule} from 'primeng/button';

const routes: Routes = [
	{ path: '', component: DashboardComponent },
];

@NgModule({
	imports: [
		CommonModule,
		FormsModule,
		ReactiveFormsModule,
		RouterModule.forChild(routes),
		InputTextModule,
		DropdownModule,
		ButtonModule,
	],
	declarations: [
		DashboardComponent,
		CountComponent
	],
	exports: [
	],
	providers: [
		DatePipe
	]
})
export class DashboardModule { }