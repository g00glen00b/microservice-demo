import {Component, OnInit} from '@angular/core';
import {ArticleService} from './article.service';
import {Articles} from './model/articles';
import {Store} from '@ngrx/store';
import {AppState} from '../shared/app-state';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html'
})
export class ArticlesComponent implements OnInit {
  offset: number = 0;
  limit: number = 10;
  articles: Articles;
  authenticated: boolean;

  constructor(private _service: ArticleService, private _store: Store<AppState>) { }

  ngOnInit() {
    this.findAll(this.offset, this.limit);
    this._store
      .select(state => state.auth)
      .subscribe(authentication => this.authenticated = authentication.claims != null);
  }

  findAll(offset: number, limit: number) {
    this._service.findAll(offset, limit).subscribe(articles => this.articles = articles);
  }

}
