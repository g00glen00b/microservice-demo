import {Injectable} from '@angular/core';
import {GlobalState} from './globalState';
import {Store} from '@ngrx/store';
import * as jwt_decode from 'jwt-decode';
import {LOGIN, LOGIN_FAILED, LOGIN_IN_PROGRESS, LOGOUT} from './auth.reducer';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/share';
import {Observable} from 'rxjs';

const tokenKey = 'blog.auth.token';

@Injectable()
export class AuthenticationService {

  constructor(private _store: Store<GlobalState>, private _http: Http) {
  }

  login(username: string, password: string, rememberMe: boolean): Observable<string> {
    let headers = new Headers();
    headers.append('Authorization', 'Basic ' + btoa(username + ':' + password));
    this._store.dispatch({ type: LOGIN_IN_PROGRESS });
    let observable = this._http.get('http://localhost:8005/uaa-service/api/token', {headers: headers})
      .map(response => response.text())
      .share();
    observable
      .do(token => this.saveToken(token, rememberMe))
      .subscribe(token => this.dispatchToken(token),
        err => this._store.dispatch({ type: LOGIN_FAILED, payload: { message: this.getMessage(err) } }));
    return observable;
  }

  logout() {
    localStorage.removeItem(tokenKey);
    sessionStorage.removeItem(tokenKey);
    this._store.dispatch({ type: LOGOUT });
  }

  getToken(): string {
    let token = sessionStorage.getItem(tokenKey);
    if (token == null) {
      token = localStorage.getItem(tokenKey);
    }
    return token;
  }

  getClaims(token: string) {
    return jwt_decode(token);
  }

  init() {
    let token = this.getToken();
    if (token != null) {
      this.dispatchToken(token);
    }
  }

  dispatchToken(token: string) {
    this._store.dispatch({ type: LOGIN, payload: { token: token, claims: this.getClaims(token), error: null } });
  }

  saveToken(token: string, rememberMe: boolean) {
    if (rememberMe) {
      localStorage.setItem(tokenKey, token);
    } else {
      sessionStorage.setItem(tokenKey, token);
    }
  }

  getMessage(error) {
    if (error.status == 401) {
      return 'Username/Password wrong.'
    } else {
      return 'Unknown issue, try again later.';
    }
  }
}
