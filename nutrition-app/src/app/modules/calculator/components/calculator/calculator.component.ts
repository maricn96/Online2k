import { MatSnackBar } from '@angular/material';
import { CalculationService } from './../../services/calculation.service';
import { FoodService } from './../../services/food.service';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  myControl = new FormControl();
  allFoodNames: string[] = ["Start typing.."];
  allFood: any[];
  filteredFoodNames: Observable<string[]>;

  //KALKULACIJA OD STRANE KORISNIKA
  calories: String = '0';
  proteins: String = '0';
  carbs: String = '0';
  fat: String = '0';

  constructor(private foodService: FoodService, private calcService: CalculationService, private snackBar: MatSnackBar,
    private router: Router) {

  }

  ngOnInit() {
    this.foodService.getAllFood().subscribe(data => {
      this.allFood = data;
    })
    this.foodService.getAllFoodNames().subscribe(data => {
      this.allFoodNames = data;
    })

    this.calcService.loadStateByUser('5dbc4e7300e6ee3db82eff40').subscribe(data => {
      this.calories = data.calories;
      this.proteins = data.proteins;
      this.carbs = data.carbs;
      this.fat = data.fat;
    })

    //za select
    this.filteredFoodNames = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }

  //za select
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allFoodNames.filter(option => option.toLowerCase().includes(filterValue));
  }


  //KALKULACIJA ZA TRENUTNU ITERACIJU
  calculate(event) {
    event.preventDefault();
    console.log('calculate()');

    var target = event.target;

    let name = target.querySelector('#name').value;
    let amount: number = target.querySelector('#amount').value;
    let user = '5dbc4e7300e6ee3db82eff40';
    let date = '2019-02-02';
    let saved = false;


    this.allFood.forEach(element => {
      if (element.name == name) {
        //mora ovako duze da fiksiram na 2 decimale (mozda bolje na int sve da se prebaci..)
        let calN: number = +this.calories;
        calN += (amount / 100)*element.calories;
        this.calories = calN.toFixed(2);

        let protN: number = +this.proteins;
        protN += (amount / 100)*element.proteins;
        this.proteins = protN.toFixed(2);

        let carbN: number = +this.carbs;
        carbN += (amount / 100)*element.carbs;
        this.carbs = carbN.toFixed(2);

        let fatN: number = +this.fat;
        fatN += (amount / 100)*element.fat;
        this.fat = fatN.toFixed(2);

      }
    });

    this.calcService.addNewCurrentCalculation(date, user, amount, name, saved).subscribe(res => {
      this.snackBar.open("Submitted", "OK", { duration: 2500 });
    })

    target.querySelector('#name').value = '';
    target.querySelector('#amount').value = '';
  }


  //FINALNA KALKULACIJA (SAVE BUTITN)
  calculateFinal() {
    event.preventDefault();
    console.log('calculateFinal()');

    //za sad fiksirano za usera
    let user: String = '5dbc4e7300e6ee3db82eff40';
    let dateOfCalculation: String = '2019-02-02';
    this.calcService.getAllUnsavedMealsByUser(user).subscribe(data => {

      
      let meals: string[] = [];
      data.forEach(element => {
        console.log(element)
        meals.push(element.id);
      });
      

      this.calcService.addNewCalculation(meals, dateOfCalculation, user).subscribe(data => {
        this.calories = '0';
        this.proteins = '0';
        this.carbs = '0';
        this.fat = '0';
        this.router.navigateByUrl('/addnew', {skipLocationChange: true}).then(()=>
        this.router.navigate(["/calculator"]));
        this.snackBar.open("Successfully saved", "OK", { duration: 2500 });
  
      })
    })

    

  }

}
