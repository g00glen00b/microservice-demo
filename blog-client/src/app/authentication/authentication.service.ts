import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import * as jwt_decode from 'jwt-decode';
import {INIT, LOGIN, LOGIN_FAILED, LOGIN_IN_PROGRESS, LOGOUT} from './auth.reducer';
import {Headers, Http} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/share';
import {Observable} from 'rxjs';
import {Authentication} from './authentication';

const tokenKey = 'blog.auth.token';

@Injectable()
export class AuthenticationService {

  constructor(private _store: Store<Authentication>, private _http: Http) {
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

  init() {
    let token = this.getToken();
    if (token != null) {
      this.dispatchToken(token);
    }
  }

  private dispatchToken(token: string) {
    let claims = this.getClaims(token);
    console.log(claims);
    if (claims['exp'] < new Date().getTime()/1000) {
      this.logout();
    } else {
      this._store.dispatch({type: LOGIN, payload: {token: token, claims: claims, error: null}});
    }
  }

  private getToken(): string {
    let token = sessionStorage.getItem(tokenKey);
    if (token == null) {
      token = localStorage.getItem(tokenKey);
    }
    return token;
  }

  private getClaims(token: string) {
    return jwt_decode(token);
  }

  private saveToken(token: string, rememberMe: boolean) {
    if (rememberMe) {
      localStorage.setItem(tokenKey, token);
    } else {
      sessionStorage.setItem(tokenKey, token);
    }
  }

  private getMessage(error) {
    if (error.status == 401) {
      return 'Username/Password wrong.'
    } else {
      return 'Unknown issue, try again later.';
    }
  }
}
