import {Component, OnInit} from '@angular/core';
import {Article} from '../model/article';
import {ActivatedRoute} from '@angular/router';
import {ArticleService} from '../article.service';
import {Alert, ALERT_ERROR_LEVEL, ALERT_SUCCESS_LEVEL} from '../../shared/alert/alert';
import {ALERT_SENT} from '../../shared/alert/app-alert.reducer';
import {AppState} from '../../shared/app-state';
import {Store} from '@ngrx/store';

@Component({
  selector: 'app-edit-article',
  templateUrl: './edit-article.component.html'
})
export class EditArticleComponent implements OnInit {
  article: Article;

  constructor(private _route: ActivatedRoute, private _service: ArticleService, private _store: Store<AppState>) { }

  ngOnInit() {
    this._route.params
      .map(params => params['slug'])
      .flatMap(slug => this._service.findOne(slug))
      .subscribe(article => this.article = article,
        () => this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The article could not be retrieved')}));
  }

  update(article: Article) {
    this.article.text = article.text;
    this.article.title = article.title;
    this._service
      .update(this.article)
      .subscribe(
        () => this._store.dispatch({type: ALERT_SENT, payload: new Alert(ALERT_SUCCESS_LEVEL, 'The article has been saved')}),
        () => this._store.dispatch({type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The article could not be saved.')}));
  }
}
