import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ArticleService} from '../article.service';
import {Article} from '../model/article';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import {Store} from '@ngrx/store';
import {AppState} from '../../shared/app-state';
import {ALERT_SENT} from '../../shared/alert/app-alert.reducer';
import {Alert, ALERT_ERROR_LEVEL, ALERT_SUCCESS_LEVEL} from '../../shared/alert/alert';
import {ProfileService} from '../../profiles/profile.service';
import {Profile} from '../../profiles/model/profile';
import {Authentication} from '../../authentication/authentication';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html'
})
export class ArticleDetailComponent implements OnInit {
  article: Article;
  profile: Profile;
  authentication: Authentication;

  constructor(private _route: ActivatedRoute, private _service: ArticleService, private _profileService: ProfileService,
              private _store: Store<AppState>, private _router: Router) { }

  ngOnInit() {
    this._route.params
      .map(params => params['slug'])
      .flatMap(slug => this._service.findOne(slug))
      .subscribe(article => this.updateArticle(article),
        () => this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The article could not be retrieved')}));
    this._store
      .select(state => state.auth)
      .subscribe(authentication => this.authentication = authentication);
  }

  isAuthorized(article: Article) {
    return article != null && this.authentication.claims != null && article.username == this.authentication.claims.sub;
  }

  remove(article: Article) {
    this._service
      .remove(article)
      .subscribe(() => {
        this._router.navigate(['/articles']);
        this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_SUCCESS_LEVEL, 'The article has been removed') });
      }, () => this._store.dispatch({ type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The article could not be removed') }));
  }

  private updateArticle(article: Article) {
    this.article = article;
    this._profileService.findOne(article.username).subscribe(
      profile => this.profile = profile,
      () => this._store.dispatch({type: ALERT_SENT, payload: new Alert(ALERT_ERROR_LEVEL, 'The profile of the author could not be retrieved')}));
  }
}
