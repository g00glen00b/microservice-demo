import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ArticleService} from '../article.service';
import {Article} from '../model/article';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html'
})
export class ArticleDetailComponent implements OnInit {
  article: Article;
  constructor(private _route: ActivatedRoute, private _service: ArticleService) { }

  ngOnInit() {
    this._route.params
      .map(params => params['slug'])
      .flatMap(slug => this._service.findOne(slug))
      .subscribe(article => this.article = article);
  }

}
