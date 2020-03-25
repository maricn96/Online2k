import { LoginService } from './../../modules/services/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private logged: LoginService, private router: Router) { }

  ngOnInit() {
    //proveravamo sesiju (ako se nije izlogovao a npr pogasio je brauzer)
    if(localStorage.getItem('token') != null)
      this.logged.setLoggedIn(true)
    
  }

  ulogovan(): boolean
  {
    return this.logged.isLoggedIn;
  }

  logout()
  {
    localStorage.removeItem('token');
    this.logged.setLoggedIn(false);
    this.router.navigate['login'];

  }

}
