import {Component, Input, OnChanges} from '@angular/core';
import {Article} from '../article';

@Component({
  selector: 'app-article-excerpt',
  templateUrl: './article-excerpt.component.html'
})
export class ArticleExcerptComponent implements OnChanges {
  @Input() article: Article;
  excerpt: string;

  constructor() { }

  ngOnChanges() {
    if (this.article != null) {
      this.excerpt = this.article.getExcerpt();
    }
  }

}
