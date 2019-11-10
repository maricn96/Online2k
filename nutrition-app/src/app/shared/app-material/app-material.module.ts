import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
    MatToolbarModule, MatSliderModule, MatButtonModule, MatSelectModule,
    MatIconModule, MatSidenavModule, MatListModule,
    MatGridListModule, MatExpansionModule, MatSortModule, MatTableModule, MatOptionModule, MatSnackBarModule, MatDialogModule, MatInputModule, MatCheckboxModule, MatNativeDateModule,
    MatDatepickerModule, MatPaginatorModule, MatCardModule, MatMenuModule, MatAutocompleteModule
} from '@angular/material';

@NgModule({
    imports: [
        CommonModule,
        MatToolbarModule,
        MatSliderModule,
        MatButtonModule,
        MatSelectModule,
        MatInputModule,
        MatIconModule,
        MatSidenavModule, MatListModule,
        MatAutocompleteModule,
    MatGridListModule, MatExpansionModule, MatSortModule, MatTableModule, MatOptionModule, MatSnackBarModule, MatDialogModule, MatInputModule, MatCheckboxModule, MatNativeDateModule,
    MatDatepickerModule, MatPaginatorModule, MatCardModule, MatMenuModule
    ],
    exports: [
        MatToolbarModule,
        MatSliderModule,
        MatButtonModule,
        MatSelectModule,
        MatInputModule,
        MatIconModule,
        MatSidenavModule, MatListModule, MatAutocompleteModule,
    MatGridListModule, MatExpansionModule, MatSortModule, MatTableModule, MatOptionModule, MatSnackBarModule, MatDialogModule, MatInputModule, MatCheckboxModule, MatNativeDateModule,
    MatDatepickerModule, MatPaginatorModule, MatCardModule, MatMenuModule
    ]
})
export class AppMaterialModule { }