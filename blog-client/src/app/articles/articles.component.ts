import {Component, OnInit} from '@angular/core';
import {ArticleService} from './article.service';
import {Articles} from './model/articles';
import {Store} from '@ngrx/store';
import {AppState} from '../shared/app-state';
import {Alert, ALERT_ERROR_LEVEL} from '../shared/alert/alert';
import {ALERT_SENT} from '../shared/alert/app-alert.reducer';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html'
})
export class ArticlesComponent implements OnInit {
  offset: number = 0;
  limit: number = 10;
  articles: Articles;

  constructor(private _service: ArticleService, private _store: Store<AppState>) { }

  ngOnInit() {
    this.findAll(this.offset, this.limit);
  }

  findAll(offset: number, limit: number) {
    this._service.findAll(offset, limit).subscribe(articles => this.articles = articles,
      () => this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The articles could not be retrieved')}));
  }

}
