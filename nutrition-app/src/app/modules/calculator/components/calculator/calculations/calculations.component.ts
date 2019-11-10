import { DataService } from 'src/app/shared/services/data.service';
import { CalculationDetailsComponent } from './../calculation-details/calculation-details.component';
import { MatDialog } from '@angular/material';
import { CalculationService } from './../../../services/calculation.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-calculations',
  templateUrl: './calculations.component.html',
  styleUrls: ['./calculations.component.css']
})
export class CalculationsComponent implements OnInit {

  allCalculationsByUser: any[] = [];
  calculationId: string;

  constructor(private calculationService: CalculationService, public dialog: MatDialog, private dataService: DataService,
    private router: Router) { }

  ngOnInit() {
    this.dataService.currentMessage.subscribe(id => this.calculationId = id)
    this.calculationService.getAllCalculationsByUser('5dbc4e7300e6ee3db82eff40').subscribe(res => {
      this.allCalculationsByUser = res;
      //sort by date ascending
      this.allCalculationsByUser.sort((a, b) => new Date(b.dateOfCalculation).getTime() - new Date(a.dateOfCalculation).getTime());
    })
  }

  openDialog(id): void {
    console.log(id);
    this.dataService.changeCalculationId(id);
    const dialogRef = this.dialog.open(CalculationDetailsComponent, {
      width: '350px',
      height: '450px'
    });
  }

  deleteCalculation(id): void {
    this.calculationService.deleteCalculation(id).subscribe(res => {
      this.router.navigateByUrl('/addnew', { skipLocationChange: true }).then(() =>
        this.router.navigate(["/calculator"])); 
      (res) ? (alert("Deleted")) : (alert("Error"));
    })
  }

  downloadCalculation(id): void {

  }

}
