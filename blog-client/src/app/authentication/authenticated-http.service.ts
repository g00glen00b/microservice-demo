import {Injectable} from '@angular/core';
import {
  Http, RequestOptionsArgs, Request, Response, ConnectionBackend, RequestOptions, Headers,
  XHRBackend
} from '@angular/http';
import {Observable} from 'rxjs';
import {AppState} from '../shared/app-state';
import {Store} from '@ngrx/store';

@Injectable()
export class AuthenticatedHttp extends Http {
  token: string = null;

  constructor(backend: ConnectionBackend, defaultOptions: RequestOptions, private _store: Store<AppState>) {
    super(backend, defaultOptions);
    this._store.select(state => state.auth).subscribe(authentication => this.token = authentication.token);
  }

  request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
    return super.request(url, this.getTokenOptions(options));
  }


  post(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
    return super.post(url, body, this.getTokenOptions(options));
  }

  put(url: string, body: any, options?: RequestOptionsArgs): Observable<Response> {
    return super.put(url, body, this.getTokenOptions(options));
  }

  getTokenOptions(options?: RequestOptionsArgs) {
    let tokenOptions = options == null ? { headers: new Headers() } : options;
    tokenOptions.headers.append('X-Token', this.token);
    return tokenOptions;
  }

  static factory(backend: XHRBackend, defaultOptions: RequestOptions, store: Store<AppState>) {
    return new AuthenticatedHttp(backend, defaultOptions, store);
  }
}

export const httpFactory = AuthenticatedHttp.factory;
