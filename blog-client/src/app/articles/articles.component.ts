import { Component, OnInit } from '@angular/core';
import {ArticleService} from './article.service';
import {Articles} from './model/articles';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html'
})
export class ArticlesComponent implements OnInit {
  offset: number = 0;
  limit: number = 10;
  articles: Articles;

  constructor(private _service: ArticleService) { }

  ngOnInit() {
    this.findAll(this.offset, this.limit);
  }

  findAll(offset: number, limit: number) {
    this._service.findAll(offset, limit).subscribe(articles => this.articles = articles);
  }

}
