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

  constructor(private loginService: LoginService, public snackBar: MatSnackBar, private router: Router) { }

  ngOnInit() {
  }

  login(event) {
    event.preventDefault();
    console.log('login()');

    var target = event.target;

    let username = target.querySelector('#username').value;
    let password = target.querySelector('#password').value;

    //nece da posalje preko dto -.-
    this.loginService.login(username, password).subscribe(data => {
      this.snackBar.open("Food successfully inserted", "OK", { duration: 2500 });
      this.router.navigateByUrl('/calculator', {skipLocationChange: true}).then(()=>
      this.router.navigate(["/addnew"])); //switch to skill list view
    })
  }
    
}
