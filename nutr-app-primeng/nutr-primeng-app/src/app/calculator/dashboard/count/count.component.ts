import { DropDownItem } from './../../../model/dropdownitem';
import { Food } from './../../../model/food';
import { ApiCalculatorService } from './../../../services/api-calculator.service';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { FormGroup, FormControl } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-count',
  templateUrl: './count.component.html',
  styleUrls: ['./count.component.css']
})
export class CountComponent implements OnInit {

  selFood: Food;
  allFoodApi: Food[];
  allFoodDrop: any[];
  filteredFood: any[];
  loadedCalculation: any;
  dto: any;

  mealForm: FormGroup;

  getAllFoodSubscriber: Subscription;
  saveMealSubscriber: Subscription;
  loadUnsavedCalcSubscriber: Subscription;

  constructor(private apiService: ApiCalculatorService,
    private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.initForm();
    this.loadUnsavedCalculation();
    this.getAllFood();
  }

  ngOnDestroy() {
    this.saveCurrentCalculation();
    this.getAllFoodSubscriber ? this.getAllFoodSubscriber.unsubscribe : null;
    this.saveMealSubscriber ? this.saveMealSubscriber.unsubscribe : null;
  }

  initForm() {
    this.mealForm = new FormGroup({
      name: new FormControl(''),
      amountIns: new FormControl(''),
      calories: new FormControl(''),
      proteins: new FormControl(''),
      carbs: new FormControl(''),
      fat: new FormControl(''),
      currCalories: new FormControl(0),
      currProteins: new FormControl(0),
      currCarbs: new FormControl(0),
      currFat: new FormControl(0)
    })
  }

  loadUnsavedCalculation() {
    //stavi neki kao hint u vidu fade out kao malo uputstvo kako se koristi ovo
    this.loadUnsavedCalcSubscriber = this.apiService.loadUnsavedCalculation('5dbc4e7300e6ee3db82eff40').subscribe(res => {
      this.mealForm.get('calories').setValue(res.calories);
      this.mealForm.get('proteins').setValue(res.proteins);
      this.mealForm.get('carbs').setValue(res.carbs);
      this.mealForm.get('fat').setValue(res.fat);
    })
  }

  getAllFood() {
    this.getAllFoodSubscriber = this.apiService.getAllFood().subscribe((data: Food[]) => {
      console.log(data);
      this.allFoodApi = data.map(food => {
        return new Food().deserialize(food);
      })
      this.allFoodDrop = data.map(i => new DropDownItem(i['name'], i['id']));
      this.allFoodDrop = [new DropDownItem('Select..', ''), ...this.allFoodDrop];
    })
  }

  changeFoodDrop(selFoodVal: any) {
    this.allFoodApi.forEach(food => {
      if(selFoodVal.value === food.id)  
        this.selFood = food;
    })
    console.log(this.selFood);
  }

  calculateNutrients() {
    if(this.selFood == undefined) {
      return;
    }
    console.log(this.selFood);
    let amountToCalc: number = this.mealForm.get('amountIns').value / 100;
    let currCalories: number = amountToCalc * this.selFood.calories;
    let currProteins: number = amountToCalc * this.selFood.proteins;
    let currCarbs: number = amountToCalc * this.selFood.carbs;
    let currFat: number = amountToCalc * this.selFood.fat;
    let newCalories: number = Number(this.mealForm.get('calories').value) + currCalories;
    let newProtein: number = Number(this.mealForm.get('proteins').value) + currProteins;
    let newCarbs: number = Number(this.mealForm.get('carbs').value) + currCarbs;
    let newFat: number = Number(this.mealForm.get('fat').value) + currFat;
    this.mealForm.get('calories').setValue(newCalories.toFixed(2));
    this.mealForm.get('proteins').setValue(newProtein.toFixed(2));
    this.mealForm.get('carbs').setValue(newCarbs.toFixed(2));
    this.mealForm.get('fat').setValue(newFat.toFixed(2));
    this.mealForm.get('currCalories').setValue(currCalories.toFixed(2));
    this.mealForm.get('currProteins').setValue(currProteins.toFixed(2));
    this.mealForm.get('currCarbs').setValue(currCarbs.toFixed(2));
    this.mealForm.get('currFat').setValue(currFat.toFixed(2));

    this.dto = new Object();
    this.dto['dateOfMeal'] = this.datePipe.transform(new Date(), 'dd.MM.yyyy');
    console.log(this.dto.dateOfMeal);
    this.dto['user'] = '5dbc4e7300e6ee3db82eff40';
    this.dto['amount'] = this.mealForm.get('amountIns').value;
    this.dto['food'] = this.selFood.name;
    this.dto['saved'] = false;
    console.log(this.dto);
    this.saveCurrentCalculation();    
  }

  saveCurrentCalculation() {
  this.saveMealSubscriber = this.apiService.addNewCurrentCalculation(this.dto).subscribe(res => {
    console.log(res);
})
  }

  saveMeal() {

  }

  resetForm() {
    this.mealForm.reset();
  }


}
