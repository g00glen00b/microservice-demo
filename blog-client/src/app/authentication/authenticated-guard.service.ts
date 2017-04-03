import {Injectable} from '@angular/core';
import {CanActivate} from '@angular/router';
import {Store} from '@ngrx/store';
import {AppState} from './app-state';

@Injectable()
export class AuthenticatedGuard implements CanActivate {
  authenticated: boolean = false;

  constructor(private _store: Store<AppState>) {
    this._store
      .select(state => state.auth)
      .subscribe(authentication => this.authenticated = authentication.claims != null);
  }

  canActivate(): boolean {
    return this.authenticated;
  }
}
