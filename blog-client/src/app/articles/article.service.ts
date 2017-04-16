import {Injectable} from '@angular/core';
import {Articles} from './model/articles';
import {Observable} from 'rxjs';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import {MarkdownService} from './markdown.service';
import {Article} from './model/article';
import * as _ from 'lodash';
import {AuthenticatedHttp} from '../authentication/authenticated-http.service';

const baseUrl: string = 'http://localhost:8005/blog-service/api/article';
const excerptLength: number = 200;

@Injectable()
export class ArticleService {

  constructor(private _http: Http, private _authenticatedHttp: AuthenticatedHttp, private _markdown: MarkdownService) {
  }

  findAll(offset: number = 0, limit: number = 10, search: string = "", username: string = ""): Observable<Articles> {
    return this._http
      .get(`${baseUrl}?offset=${offset}&limit=${limit}&search=${search}&username=${username}`)
      .map(response => response.json())
      .map(response => Articles.fromResponse(response));
  }

  findOne(slug: string): Observable<Article> {
    return this._http
      .get(`${baseUrl}/${slug}`)
      .map(response => response.json())
      .map(response => Article.fromResponse(response));
  }

  save(article: Article): Observable<Article> {
    return this._authenticatedHttp
      .post(`${baseUrl}`, article)
      .map(response => response.json())
      .map(response => Article.fromResponse(response));
  }

  remove(article: Article): Observable<void> {
    return this._authenticatedHttp
      .delete(`${baseUrl}/${article.slug}`)
      .map(response => null);
  }

  getExcerpt(article: Article) {
    return _.truncate(this._markdown.strip(article.text), {length: excerptLength});
  }
}
