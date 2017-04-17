import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Profile} from './model/profile';
import {Observable} from 'rxjs/Observable';

export const baseUrl: string = 'http://localhost:8005/profile-service/api/profile';

@Injectable()
export class ProfileService {

  constructor(private _http: Http) { }

  findOne(username: string): Observable<Profile> {
    return this._http.get(`${baseUrl}/${username}`)
      .map(response => response.json())
      .map(response => Profile.fromResponse(response));
  }
}
