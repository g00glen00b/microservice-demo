import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  constructor(private _service: AuthenticationService) { }

  ngOnInit() {
  }

}
