import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ArticleService} from '../article.service';
import {Article} from '../model/article';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';
import {Store} from '@ngrx/store';
import {AppState} from '../../authentication/app-state';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html'
})
export class ArticleDetailComponent implements OnInit {
  article: Article;
  authenticated: boolean = false;

  constructor(private _route: ActivatedRoute, private _service: ArticleService, private _store: Store<AppState>,
              private _router: Router) { }

  ngOnInit() {
    this._route.params
      .map(params => params['slug'])
      .flatMap(slug => this._service.findOne(slug))
      .subscribe(article => this.article = article);
    this._store
      .select(state => state.auth)
      .subscribe(authentication => this.authenticated = authentication.claims != null);
  }

  remove(article: Article) {
    this._service
      .remove(article)
      .subscribe(() => this._router.navigate(['/articles']));
  }

}
