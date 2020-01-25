import { MatSnackBar } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private regService: RegisterService, public snackBar: MatSnackBar, private router: Router) { }

  ngOnInit() {
  }

  register(event) {
    event.preventDefault();
    console.log('register()');

    var target = event.target;

    let email = target.querySelector('#email').value;
    let password = target.querySelector('#password').value;
    let passwordRepeated = target.querySelector('#passwordRepeated').value;


    //nece da posalje preko dto -.-
    this.regService.register(email, password, passwordRepeated).subscribe(data => {
      console.log(data);
      //na neku foru ne ispisuje vracene podatke (String) sa api-ja i ne ulazi uopste u ovo, nz za kaj
      // if(data === "PASS_ERR")
      //   alert("Passwords doesn't match!");
      // else if(data === "EXISTS_ERR")
      //   alert("User with that email already exists!")
      // else
      //   this.snackBar.open("Successfully registered", "OK", { duration: 2500 });

      // this.router.navigateByUrl('/addnew', {skipLocationChange: true}).then(()=>
      // this.router.navigate(["/register"]));
    })
  }
    

  klik() {
    this.regService.test().subscribe(data => {
      console.log(data)
    })
  }
}
