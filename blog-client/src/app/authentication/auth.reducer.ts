import {Action, ActionReducer} from '@ngrx/store';
import {Authentication} from './authentication';


export const LOGIN = 'LOGIN';
export const LOGIN_FAILED = 'LOGIN_FAILED';
export const LOGIN_IN_PROGRESS = 'LOGIN_IN_PROGRESS';
export const LOGOUT = 'LOGOUT';
export const INIT = 'INIT';

let initialState: Authentication = {
  token: null,
  claims: null,
  error: null
};

export const auth: ActionReducer<Authentication> = (state: Authentication = initialState, action: Action = { type: INIT}) => {
  switch (action.type) {
    case LOGIN:
      return { token: action.payload.token, claims: action.payload.claims, error: null };
    case LOGIN_FAILED:
      return { token: null, claims: null, error: action.payload.message };
    case LOGOUT:
    case INIT:
    case LOGIN_IN_PROGRESS:
      return { token: null, claims: null, error: null };
    default:
      return state;
  }
};
