import {Component, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import {AuthenticationService} from '../../authentication/authentication.service';
import {Router} from '@angular/router';
import {AppState} from '../app-state';
import {ProfileService} from '../../profiles/profile.service';
import {Profile} from '../../profiles/model/profile';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/observable/of';
import {Observable} from 'rxjs/Observable';
import {Authentication} from "app/authentication/authentication";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {
  profile: Profile;
  constructor(private _store: Store<AppState>, private _service: AuthenticationService, private _profileService: ProfileService, private _router: Router) { }

  ngOnInit() {
    this._store.select(state => state.auth)
      .switchMap(authentication => this.getProfile(authentication))
      .subscribe(profile => {console.log(profile); this.profile = profile; });
  }

  logout() {
    this._service.logout();
    this._router.navigate(['/home']);
    event.preventDefault();
  }

  getProfile(auth: Authentication): Observable<Profile> {
    if (auth.claims == null) {
      return Observable.of(null);
    } else {
      return this._profileService.findMe();
    }
  }
}
