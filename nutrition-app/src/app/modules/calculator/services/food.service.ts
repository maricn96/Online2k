import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { NewFoodDTO } from '../dto/newFoodDTO';


@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private extractData(res: Response)
  {
    let body = res;
    return body || {};
  }

  router: String = 'http://localhost:8086';

  constructor(private http: HttpClient) { }

  getAllFood(): Observable<any> {
    return this.http.get(this.router + '/food').pipe(map(this.extractData));
  }

  getAllFoodNames(): Observable<any> {
    return this.http.get(this.router + '/food/names').pipe(map(this.extractData));
  }

  /**
   * Dodavanje novih namirnica 
   * @param dto (ADMIN)
   */
  addNewFood(name, calories, proteins, carbs, fat): Observable<any> {
    return this.http.post(this.router + '/food', {
      name, calories, proteins, carbs, fat
    }).pipe(map(this.extractData));
  }



}
