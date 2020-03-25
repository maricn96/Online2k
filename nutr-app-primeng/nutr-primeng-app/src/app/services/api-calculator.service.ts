import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiCalculatorService {

  foodRouter: string = 'http://localhost:8086/food/';
  calculationRouter: string = 'http://localhost:8086/calculation/';

  constructor(private httpClient: HttpClient) { }

  getAllFoodUrl: string = this.foodRouter;

  addNewCurrentCalculationUrl: string = this.calculationRouter + 'currentcalc';
  loadUnsavedCalculationUrl: string = this.calculationRouter + 'load/'

  getAllFood() {
    return this.httpClient.get<any[]>(this.getAllFoodUrl)
  }

  addNewCurrentCalculation(dto: any) {
    return this.httpClient.post<any>(this.addNewCurrentCalculationUrl, dto);
  }

  loadUnsavedCalculation(userid: any) {
    return this.httpClient.get<any>(this.loadUnsavedCalculationUrl + userid)
  }





  // getNazivGp(polisaId: number) {
	// 	return this.httpClient.get<any>(this.getNazivGpUrl + polisaId, { responseType: 'text' as 'json' })
	// 		.catch((error: any) => {
	// 			if (error.status === 500) {
	// 				return observableThrowError(error);
	// 			}
	// 		});
	// }


}
