import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {CanActivate} from '@angular/router';
import {AppState} from '../../shared/app-state';

@Injectable()
export class UnauthenticatedGuard implements CanActivate {
  unauthenticated: boolean = false;

  constructor(private _store: Store<AppState>) {
    this._store
      .select(state => state.auth)
      .subscribe(authentication => this.unauthenticated = authentication.claims == null);
  }

  canActivate(): boolean {
    return this.unauthenticated;
  }
}
