import { CalculationService } from './../../../services/calculation.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material';
import { DataService } from 'src/app/shared/services/data.service';

@Component({
  selector: 'app-calculation-details',
  templateUrl: './calculation-details.component.html',
  styleUrls: ['./calculation-details.component.css']
})
export class CalculationDetailsComponent implements OnInit {

  meals: any[] = [];
  calculationId: string; //ovo povuci od sibling klase

  constructor(
    public dialogRef: MatDialogRef<CalculationDetailsComponent>, private calculationService: CalculationService,
    private dataService: DataService) { }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(id => this.calculationId = id)
    this.calculationService.getAllMealsByCalculation(this.calculationId).subscribe(res => {
      this.meals = res;
    })
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


}
