import {Component, OnInit} from '@angular/core';
import {GlobalState} from '../../authentication/globalState';
import {Store} from '@ngrx/store';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {
  authentication;
  constructor(private _store: Store<GlobalState>) { }

  ngOnInit() {
    this._store.select('auth').subscribe(authentication => this.authentication = authentication);
  }

}
