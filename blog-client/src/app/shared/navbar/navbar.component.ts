import {Component, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import {AuthenticationService} from '../../authentication/authentication.service';
import {Router} from '@angular/router';
import {AppState} from '../app-state';
import {ProfileService} from '../../profiles/profile.service';
import {Profile} from '../../profiles/model/profile';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/filter';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {
  profile: Profile;
  constructor(private _store: Store<AppState>, private _service: AuthenticationService, private _profileService: ProfileService, private _router: Router) { }

  ngOnInit() {
    this._store.select(state => state.auth)
      .filter(authentication => authentication.claims != null)
      .switchMap(authentication => this._profileService.findMe())
      .subscribe(profile => this.profile = profile);
  }

  logout() {
    this._service.logout();
    this._router.navigate(['/home']);
    event.preventDefault();
  }

}
