import {Action, ActionReducer} from '@ngrx/store';
import {Authentication} from './authentication';


export const LOGIN = 'LOGIN';
export const LOGOUT = 'LOGOUT';
export const INIT = 'INIT';

let initialState: Authentication = {
  token: null,
  claims: null
};

export const auth: ActionReducer<Authentication> = (state: Authentication = initialState, action: Action = { type: INIT}) => {
  switch (action.type) {
    case LOGIN:
      return { token: action.payload.token, claims: action.payload.claims };
    case LOGOUT:
    case INIT:
      return { token: null, claims: null };
    default:
      return state;
  }
};
