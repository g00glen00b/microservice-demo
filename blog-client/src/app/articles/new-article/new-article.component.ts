import {Component, OnInit} from '@angular/core';
import {Article} from '../model/article';
import {ArticleService} from '../article.service';
import {Router} from '@angular/router';
import {AppState} from '../../shared/app-state';
import {Store} from '@ngrx/store';
import {ALERT_SENT} from '../../shared/alert/app-alert.reducer';
import {Alert, ALERT_ERROR_LEVEL, ALERT_SUCCESS_LEVEL} from '../../shared/alert/alert';

@Component({
  selector: 'app-new-article',
  templateUrl: './new-article.component.html'
})
export class NewArticleComponent implements OnInit {
  article: Article = new Article();
  constructor(private _service: ArticleService, private _router: Router, private _store: Store<AppState>) { }

  ngOnInit() {
  }

  create(article: Article) {
    this._service.save(article).subscribe(() => {
      this._router.navigate(['/articles']);
      this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_SUCCESS_LEVEL, 'The article has been created') })
    }, () => this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The article could not be created') }));
  }
}
