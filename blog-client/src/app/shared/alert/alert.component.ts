import {Component, OnInit} from '@angular/core';
import {Store} from '@ngrx/store';
import {AppState} from '../app-state';
import {Alert, ALERT_ERROR_LEVEL, ALERT_INFO_LEVEL, ALERT_SUCCESS_LEVEL, ALERT_WARN_LEVEL} from './alert';
import {ALERT_DISMISSED} from './app-alert.reducer';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html'
})
export class AlertComponent implements OnInit {
  alert: Alert;
  alertClass: AlertClass = {'alert': true, 'alert-success': false, 'alert-info': false, 'alert-warn': false, 'alert-danger': false};
  constructor(private _store: Store<AppState>) { }

  ngOnInit() {
    this._store.select(store => store.appAlert).subscribe(alert => this.updateAlert(alert));
  }

  dismissAlert() {
    this._store.dispatch({ type: ALERT_DISMISSED });
  }

  private updateAlert(alert: Alert) {
    this.alert = alert;
    this.alertClass['alert-success'] = alert.level == ALERT_SUCCESS_LEVEL;
    this.alertClass['alert-info'] = alert.level == ALERT_INFO_LEVEL;
    this.alertClass['alert-warn'] = alert.level == ALERT_WARN_LEVEL;
    this.alertClass['alert-danger'] = alert.level == ALERT_ERROR_LEVEL;
  }
}

interface AlertClass {
  alert: boolean;
  'alert-success': boolean;
  'alert-info': boolean;
  'alert-warn': boolean;
  'alert-danger': boolean;
}
