import { Component, OnInit } from '@angular/core';
import {Article} from '../model/article';
import * as _ from 'lodash';
import {ArticleService} from '../article.service';

@Component({
  selector: 'app-article-new',
  templateUrl: './article-new.component.html'
})
export class ArticleNewComponent implements OnInit {
  article: Article = new Article();
  prefix: string;
  constructor(private _service: ArticleService) { }

  ngOnInit() {
    this.prefix = this.getUrlPrefix();
  }

  onSubmit() {
    this._service.save(this.article).subscribe(article => console.log(article));
  }

  createSlug(title: string) {
    if (this.article.slug == null) {
      this.article.slug = _.kebabCase(title);
    }
  }

  getUrlPrefix() {
    return window.location.protocol + '//' +
      window.location.hostname +
      (_.isEmpty(window.location.port) ? '' : ':' + window.location.port) +
      '/articles/';
  }
}
