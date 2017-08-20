import { Component, OnInit } from '@angular/core';
import {ProfileService} from '../profile.service';
import {AppState} from '../../shared/app-state';
import {Store} from '@ngrx/store';
import {Profile} from '../model/profile';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/filter';
import {Alert, ALERT_ERROR_LEVEL, ALERT_SUCCESS_LEVEL} from '../../shared/alert/alert';
import {ALERT_SENT} from '../../shared/alert/app-alert.reducer';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html'
})
export class EditProfileComponent implements OnInit {
  profile: Profile;
  constructor(private _service: ProfileService, private _store: Store<AppState>) { }

  ngOnInit() {
    this._store
      .select(store => store.auth)
      .filter(auth => auth.claims != null)
      .switchMap(auth => this._service.findMe())
      .subscribe(profile => this.profile = profile);
  }

  saveAvatar(avatar: File) {
    this._service.updateAvatar(this.profile, avatar).subscribe(
      (updatedUrl) => {
        this.profile.avatar = updatedUrl;
        this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_SUCCESS_LEVEL, 'The avatar was successfully updated') });
      }, () => this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The avatar could not be saved. Check if it is less than 1MB and has the right type.')}));
  }

  update(profile: Profile) {
    this._service.update(profile).subscribe(
      (profile) => {
        this.profile = profile;
        this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_SUCCESS_LEVEL, 'The profile was successfully updated') });
      }, () => this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The profile could not be updated.') }));
  }

}
