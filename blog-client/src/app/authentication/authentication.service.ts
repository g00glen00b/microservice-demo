import { Injectable } from '@angular/core';
import {GlobalState} from './globalState';
import {Store} from '@ngrx/store';
import * as jwt_decode from 'jwt-decode';
import {LOGIN} from './auth.reducer';

@Injectable()
export class AuthenticationService {

  constructor(private _store: Store<GlobalState>) {
  }

  login(username: string, password: string) {

  }

  getToken(): string {
    let token = sessionStorage.getItem('blog.auth.token');
    if (token == null) {
      token = localStorage.getItem('blog.auth.token');
    }
    return token;
  }

  getClaims(token: string) {
    return jwt_decode(token);
  }

  init() {
    let token = this.getToken();
    if (token != null) {
      this._store.dispatch({ type: LOGIN, payload: { token: token, claims: this.getClaims(token), error: null } });
    }
  }
}
