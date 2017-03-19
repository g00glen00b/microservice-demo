import {Injectable} from '@angular/core';
import {Articles} from './articles';
import {Observable} from 'rxjs';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';

const baseUrl: string = 'http://localhost:8005/blog-service';

@Injectable()
export class ArticleService {

  constructor(private _http: Http) { }

  findAll(search: string, username: string, offset: number = 0, limit: number = 10): Observable<Articles> {
    return this._http
      .get(`${baseUrl}?offset=${offset}&limit=${limit}&search=${search}&username=${username}`)
      .map(response => response.json())
      .map(response => Articles.fromResponse(response));
  }
}
