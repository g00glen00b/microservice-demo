import {Injectable} from '@angular/core';
import {Articles} from './articles';
import {Observable} from 'rxjs';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import {MarkdownService} from './markdown.service';
import {Article} from './article';
import * as _ from 'lodash';

const baseUrl: string = 'http://localhost:8005/blog/api/article';
const excerptLength: number = 200;

@Injectable()
export class ArticleService {

  constructor(private _http: Http, private _markdown: MarkdownService) {
  }

  findAll(offset: number = 0, limit: number = 10, search: string = "", username: string = ""): Observable<Articles> {
    return this._http
      .get(`${baseUrl}?offset=${offset}&limit=${limit}&search=${search}&username=${username}`)
      .map(response => response.json())
      .map(response => Articles.fromResponse(response));
  }

  getExcerpt(article: Article) {
    return _.truncate(this._markdown.strip(article.text), {length: excerptLength});
  }
}
