import { Injectable } from '@angular/core';
import {CanActivate} from '@angular/router';
import {GlobalState} from './globalState';
import {Store} from '@ngrx/store';

@Injectable()
export class AuthenticatedGuardService implements CanActivate {
  authentication;

  constructor(private _store: Store<GlobalState>) {
    this._store.select('auth').subscribe(authentication => this.authentication = authentication);
  }

  canActivate(): boolean {
    return this.authentication.claims != null;
  }
}
