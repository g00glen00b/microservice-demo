import {Action, ActionReducer} from '@ngrx/store';
import {Authentication} from '../authentication';
import * as jwt_decode from 'jwt-decode';


export const LOGIN = 'LOGIN';
export const LOGIN_FAILED = 'LOGIN_FAILED';
export const LOGIN_IN_PROGRESS = 'LOGIN_IN_PROGRESS';
export const LOGOUT = 'LOGOUT';
export const INIT = 'INIT';
const TOKEN_KEY = 'blog.auth.token';

export const auth: ActionReducer<Authentication> = (state: Authentication = getAuthentication(getToken()), action: Action = { type: INIT}) => {
  switch (action.type) {
    case LOGIN:
      saveToken(action.payload.token, action.payload.rememberMe);
      return getAuthentication(action.payload.token);
    case LOGIN_FAILED:
      return { token: null, claims: null, error: action.payload.message };
    case LOGOUT:
      localStorage.removeItem(TOKEN_KEY);
      sessionStorage.removeItem(TOKEN_KEY);
      return { token: null, claims: null, error: null};
    case INIT:
    case LOGIN_IN_PROGRESS:
      return { token: null, claims: null, error: null };
    default:
      return state;
  }
};

function getAuthentication(token): Authentication {
  if (token == null) {
    return { token: null, claims: null, error: null };
  } else {
    const claims = jwt_decode(token);
    if (claims['exp'] < new Date().getTime()/1000) {
      return { token: null, claims: null, error: null };
    } else {
      return {token: token, claims: jwt_decode(token), error: null};
    }
  }
}

function saveToken(token: string, rememberMe: boolean) {
  if (rememberMe) {
    localStorage.setItem(TOKEN_KEY, token);
  } else {
    sessionStorage.setItem(TOKEN_KEY, token);
  }
}

function getToken(): string {
  let token = localStorage.getItem(TOKEN_KEY);
  if (token == null) {
    token = sessionStorage.getItem(TOKEN_KEY);
  }
  return token;
}
