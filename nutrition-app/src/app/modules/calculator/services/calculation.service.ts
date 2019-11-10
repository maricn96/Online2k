import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { NewFoodDTO } from '../dto/newFoodDTO';

@Injectable({
  providedIn: 'root'
})
export class CalculationService {

  private extractData(res: Response)
  {
    let body = res;
    return body || {};
  }

  router: String = 'http://localhost:8086';

  constructor(private http: HttpClient) { }

  /**
   * Kalkulacija za trenutnu iteraciju na koju moze da se nastavlja (KORISNIK)
   */
  addNewCurrentCalculation(dateOfMeal, user, amount, food, saved): Observable<any> {
    return this.http.post(this.router + '/calculation/currentcalc', {
      dateOfMeal, user, amount, food
    }).pipe(map(this.extractData));
  }

  /**
   * Kalkulacija od strane korisnika
   * @param dto (KORISNIK)
   */
  addNewCalculation(meals, dateOfCalculation, user): Observable<any> {
    return this.http.post(this.router + '/calculation', {
      meals, dateOfCalculation, user
    }).pipe(map(this.extractData));
  }

  getAllCalculationsByUser(userid: String): Observable<any> {
    return this.http.get(this.router + '/calculation/allcalcsbyuser/' + userid).pipe(map(this.extractData));
  }

  loadStateByUser(userid: String): Observable<any> {
    return this.http.get(this.router + '/calculation/load/' + userid).pipe(map(this.extractData));
  }

  getAllUnsavedMealsByUser(userid: String): Observable<any> {
    return this.http.get(this.router + '/calculation/allunsavedmeals/' + userid).pipe(map(this.extractData));
  }

  getAllMealsByCalculation(calculationId: String): Observable<any> {
    return this.http.get(this.router + '/calculation/allmealsbycalc/' + calculationId).pipe(map(this.extractData));
  }

  deleteCalculation(calculationId: String): Observable<any> {
    return this.http.delete(this.router + '/calculation/' + calculationId).pipe(map(this.extractData));
  }

  
}
