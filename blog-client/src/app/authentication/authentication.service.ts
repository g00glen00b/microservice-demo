import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {LOGIN, LOGIN_FAILED, LOGIN_IN_PROGRESS, LOGOUT} from './reducers/auth.reducer';
import {Headers, Http} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/share';
import {Observable} from 'rxjs';
import {Authentication} from './authentication';
import {environment} from '../../environments/environment';

@Injectable()
export class AuthenticationService {

  constructor(private _store: Store<Authentication>, private _http: Http) {
  }

  login(username: string, password: string, rememberMe: boolean): Observable<string> {
    let headers = new Headers();
    headers.append('Authorization', 'Basic ' + btoa(username + ':' + password));
    this._store.dispatch({ type: LOGIN_IN_PROGRESS });
    let observable = this._http.get(environment.apiUrl + '/uaa-service/api/token', {headers: headers})
      .map(response => response.text())
      .share();
    observable.subscribe(
      token => this._store.dispatch({ type: LOGIN, payload: { token: token, rememberMe: rememberMe }}),
      err => this._store.dispatch({ type: LOGIN_FAILED, payload: { message: this.getMessage(err) } }));
    return observable;
  }

  logout() {
    this._store.dispatch({ type: LOGOUT });
  }


  private getMessage(error) {
    if (error.status == 401) {
      return 'Username/Password wrong.'
    } else {
      return 'Unknown issue, try again later.';
    }
  }
}
