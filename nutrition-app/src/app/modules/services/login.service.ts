import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loggedInStatus = false;

  private extractData(res: Response)
  {
    let body = res;
    return body || {};
  }

  router: String = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  login(email, password): Observable<any> {
    return this.http.post(this.router + '/login', {
      email, password
    }).pipe(map(this.extractData));
  }

  // private setSession(authRes)
  // {
  //   localStorage.setItem('token', authRes.setToken);
  // }

  setLoggedIn(value: boolean)
  {
    this.loggedInStatus = value;
  } 

  get isLoggedIn()
  {
    return this.loggedInStatus;
  }

}
