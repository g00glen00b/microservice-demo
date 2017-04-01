import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from './authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  constructor(private _service: AuthenticationService) {

  }

  ngOnInit() {
    this._service.init();
  }
}
