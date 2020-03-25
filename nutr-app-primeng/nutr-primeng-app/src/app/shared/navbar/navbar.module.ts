import { InputTextModule, ButtonModule } from 'primeng/primeng';
import { MenubarModule } from 'primeng/menubar';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar.component';
import {ToolbarModule} from 'primeng/toolbar';

const routes: Routes = [
	{ path: '', component: NavbarComponent },
];

@NgModule({
	imports: [
		CommonModule,
		FormsModule,
		ReactiveFormsModule,
        RouterModule.forChild(routes),
		MenubarModule,
		InputTextModule,
		ButtonModule,
		ToolbarModule
	],
	declarations: [
		NavbarComponent
	],
	exports: [
		NavbarComponent
	],
	providers: []
})
export class NavbarModule { }