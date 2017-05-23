import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Profile} from './model/profile';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment';

export const baseUrl: string = environment.apiUrl + '/profile-service/api/profile';

@Injectable()
export class ProfileService {

  constructor(private _http: Http) { }

  findOne(username: string): Observable<Profile> {
    return this._http.get(`${baseUrl}/${username}`)
      .map(response => response.json())
      .map(response => Profile.fromResponse(response));
  }
}
