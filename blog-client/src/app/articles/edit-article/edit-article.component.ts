import {Component, OnInit} from '@angular/core';
import {Article} from '../model/article';
import {ActivatedRoute} from '@angular/router';
import {ArticleService} from '../article.service';

@Component({
  selector: 'app-edit-article',
  templateUrl: './edit-article.component.html'
})
export class EditArticleComponent implements OnInit {
  article: Article;

  constructor(private _route: ActivatedRoute, private _service: ArticleService) { }

  ngOnInit() {
    this._route.params
      .map(params => params['slug'])
      .flatMap(slug => this._service.findOne(slug))
      .subscribe(article => this.article = article);
  }

  update(article: Article) {
    this.article.text = article.text;
    this.article.title = article.title;
    this._service
      .update(this.article)
      .subscribe();
  }
}
