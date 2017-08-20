import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Profile} from './model/profile';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment';
import {AuthenticatedHttp} from '../authentication/authenticated-http.service';

export const baseUrl: string = environment.apiUrl + '/profile-service/api/profile';

@Injectable()
export class ProfileService {

  constructor(private _http: Http, private _authenticatedHttp: AuthenticatedHttp) { }

  findOne(username: string): Observable<Profile> {
    return this._http.get(`${baseUrl}/${username}`)
      .map(response => response.json())
      .map(response => Profile.fromResponse(response));
  }

  findMe(): Observable<Profile> {
    return this._authenticatedHttp.get(`${baseUrl}/@me`)
      .map(response => response.json())
      .map(response => Profile.fromResponse(response));
  }

  updateAvatar(profile: Profile, file: File): Observable<string> {
    const formData = new FormData();
    formData.append('avatar', file);
    const date = new Date();
    return this._authenticatedHttp.put(`${baseUrl}/@me/avatar`, formData)
      .map(() => `${baseUrl}/${profile.username}/avatar?date=${date.getTime()}`);
  }

  update(profile: Profile): Observable<Profile> {
    return this._authenticatedHttp.put(`${baseUrl}/@me`, profile)
      .map(response => response.json())
      .map(response => Profile.fromResponse(response));
  }
}
