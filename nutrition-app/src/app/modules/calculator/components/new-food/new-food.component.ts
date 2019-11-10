import { FoodService } from './../../services/food.service';
import { Component, OnInit } from '@angular/core';
import { NewFoodDTO } from '../../dto/newFoodDTO';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-food',
  templateUrl: './new-food.component.html',
  styleUrls: ['./new-food.component.css']
})
export class NewFoodComponent implements OnInit {

  constructor(private foodService: FoodService, public snackBar: MatSnackBar, private router: Router) { }

  ngOnInit() {
  }

  addNewFood(event) {
    event.preventDefault();
    console.log('addSkill()');

    var target = event.target;

    let name = target.querySelector('#name').value;
    let calories = target.querySelector('#calories').value;
    let proteins = target.querySelector('#proteins').value;
    let carbs = target.querySelector('#carbs').value;
    let fat = target.querySelector('#fat').value;

    // let dto: NewFoodDTO = {
    //   name: name,
    //   calories: calories,
    //   proteins: proteins,
    //   carbs: carbs,
    //   fat: fat
    // }

    //nece da posalje preko dto -.-
    this.foodService.addNewFood(name, calories, proteins, carbs, fat).subscribe(data => {
      this.snackBar.open("Food successfully inserted", "OK", { duration: 2500 });
      this.router.navigateByUrl('/calculator', {skipLocationChange: true}).then(()=>
      this.router.navigate(["/addnew"])); //switch to skill list view
    })
  }
    

}
