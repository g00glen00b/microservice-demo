import {Component, Input, OnChanges} from '@angular/core';
import {Article} from '../article';
import {ArticleService} from '../article.service';

@Component({
  selector: 'app-article-excerpt',
  templateUrl: './article-excerpt.component.html'
})
export class ArticleExcerptComponent implements OnChanges {
  @Input() article: Article;
  excerpt: string;

  constructor(private _service: ArticleService) { }

  ngOnChanges() {
    if (this.article != null) {
      this.excerpt = this._service.getExcerpt(this.article);
    }
  }

}
