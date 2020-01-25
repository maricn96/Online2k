import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private extractData(res: Response)
  {
    let body = res;
    return body || {};
  }

  router: String = 'http://localhost:8081';

  private options = { headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Access-Control-Allow-Origin': '*'
  })
}

  constructor(private http: HttpClient) { }

  register(email, password, passwordRepeated): Observable<any> {
    return this.http.post(this.router + '/register', {
      email, password, passwordRepeated
    }, this.options).pipe(map(this.extractData));
  }

  test(): Observable<any> {
    return this.http.get(this.router + '/user', {
    }).pipe(map(this.extractData));
  }

}
