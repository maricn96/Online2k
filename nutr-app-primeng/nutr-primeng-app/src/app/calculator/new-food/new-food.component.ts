import { ApiCalculatorService } from './../../services/api-calculator.service';
import { DatePipe } from '@angular/common';
import { FormGroup, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-new-food',
  templateUrl: './new-food.component.html',
  styleUrls: ['./new-food.component.css']
})
export class NewFoodComponent implements OnInit {


  foodForm: FormGroup;

  saveFoodSubscriber: Subscription;

  constructor(private apiService: ApiCalculatorService,
    private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.initForm();
  }

  ngOnDestroy() {
    this.saveFoodSubscriber ? this.saveFoodSubscriber.unsubscribe : null;
  }

  initForm() {
    this.foodForm = new FormGroup({
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
    // this.loadUnsavedCalcSubscriber = this.apiService.loadUnsavedCalculation('5dbc4e7300e6ee3db82eff40').subscribe(res => {
    //   console.log(res);
    // })
  }

  getAllFood() {
    // this.getAllFoodSubscriber = this.apiService.getAllFood().subscribe((data: Food[]) => {
    //   console.log(data);
    //   this.allFoodApi = data.map(food => {
    //     return new Food().deserialize(food);
    //   })
    //   this.allFoodDrop = data.map(i => new DropDownItem(i['name'], i['id']));
    //   this.allFoodDrop = [new DropDownItem('Select..', ''), ...this.allFoodDrop];
    // })
  }

  changeFoodDrop(selFoodVal: any) {
    // this.allFoodApi.forEach(food => {
    //   if(selFoodVal.value === food.id)  
    //     this.selFood = food;
    // })
    // console.log(this.selFood);
  }

  calculateNutrients() {
    // let amountToCalc: number = this.foodForm.get('amountIns').value / 100;
    // let currCalories: number = amountToCalc * this.selFood.calories;
    // let currProteins: number = amountToCalc * this.selFood.proteins;
    // let currCarbs: number = amountToCalc * this.selFood.carbs;
    // let currFat: number = amountToCalc * this.selFood.fat;
    // let newCalories: number = Number(this.foodForm.get('calories').value) + currCalories;
    // let newProtein: number = Number(this.foodForm.get('proteins').value) + currProteins;
    // let newCarbs: number = Number(this.foodForm.get('carbs').value) + currCarbs;
    // let newFat: number = Number(this.foodForm.get('fat').value) + currFat;
    // this.foodForm.get('calories').setValue(newCalories.toFixed(2));
    // this.foodForm.get('proteins').setValue(newProtein.toFixed(2));
    // this.foodForm.get('carbs').setValue(newCarbs.toFixed(2));
    // this.foodForm.get('fat').setValue(newFat.toFixed(2));
    // this.foodForm.get('currCalories').setValue(currCalories.toFixed(2));
    // this.foodForm.get('currProteins').setValue(currProteins.toFixed(2));
    // this.foodForm.get('currCarbs').setValue(currCarbs.toFixed(2));
    // this.foodForm.get('currFat').setValue(currFat.toFixed(2));

    // let dto: any =  new Object();
    // dto['dateOfMeal'] = this.datePipe.transform(new Date(), 'dd.MM.yyyy');
    // dto['user'] = '5dbc4e7300e6ee3db82eff40';
    // dto['amount'] = this.foodForm.get('amountIns').value;
    // dto['food'] = this.selFood.name;
    // dto['saved'] = false;
    // console.log(dto);

    // this.saveMealSubscriber = this.apiService.addNewCurrentCalculation(dto).subscribe(res => {
    //   console.log(res);
    // })
  }

  saveMeal() {

  }

  resetForm() {
    this.foodForm.reset();
  }

}
