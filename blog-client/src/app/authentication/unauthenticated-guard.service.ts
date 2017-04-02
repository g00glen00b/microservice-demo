import { Injectable } from '@angular/core';
import {Store} from '@ngrx/store';
import {GlobalState} from './globalState';
import {CanActivate} from '@angular/router';

@Injectable()
export class UnauthenticatedGuardService implements CanActivate {
  authentication;

  constructor(private _store: Store<GlobalState>) {
    this._store.select('auth').subscribe(authentication => this.authentication = authentication);
  }

  canActivate(): boolean {
    return this.authentication.claims == null;
  }
}
