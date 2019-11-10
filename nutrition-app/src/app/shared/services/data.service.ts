import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private calculationId = new BehaviorSubject<string>('');
  currentMessage = this.calculationId.asObservable();

  constructor() { }

  changeCalculationId(newId: string) {
    this.calculationId.next(newId);
  }
}
