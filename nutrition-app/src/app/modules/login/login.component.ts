import { HttpInterceptor } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';
import { LoginService } from './../services/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private token: string;

  constructor(private loginService: LoginService, public snackBar: MatSnackBar, private router: Router) { }

  ngOnInit() {
  }

  login(event) {
    event.preventDefault();
    console.log('login()');

    var target = event.target;

    let email = target.querySelector('#email').value;
    let password = target.querySelector('#password').value;

    this.loginService.login(email, password).subscribe(data => {
      
      this.router.navigate(['calculator']);
      this.loginService.setLoggedIn(true);
      localStorage.setItem('token', data.toString()); 
      this.snackBar.open("Signed in", "OK", { duration: 2500 });
    })
  }

  getToken(): string
  {
    return this.token;
  }
}

export class HttpAuthInterceptor implements HttpInterceptor
{
  intercept
}
