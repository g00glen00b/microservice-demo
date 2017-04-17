import {Action, ActionReducer} from '@ngrx/store';
import {Alert} from './alert';


export const ALERT_SENT = 'ALERT_SENT';
export const ALERT_DISMISSED = 'ALERT_DISMISSED';
export const INIT = 'INIT';

let initialState: Alert = {
  level: null,
  message: null
};

export const appAlert: ActionReducer<Alert> = (state: Alert = initialState, action: Action = { type: INIT}) => {
  switch (action.type) {
    case ALERT_SENT:
      return action.payload;
    case INIT:
    case ALERT_DISMISSED:
      return { level: null, message: null };
    default:
      return state;
  }
};
