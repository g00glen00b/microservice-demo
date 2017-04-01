import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../authentication.service';
import {Store} from '@ngrx/store';
import {GlobalState} from '../globalState';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  rememberMe: boolean;
  authentication;

  constructor(private _service: AuthenticationService, private _store: Store<GlobalState>, private _router: Router) { }

  ngOnInit() {
    this._store.select('auth').subscribe(authentication => this.authentication = authentication);
  }

  onLogin() {
    this._service.login(this.username, this.password, this.rememberMe).subscribe(() => this._router.navigate(['/home']));
  }
}
