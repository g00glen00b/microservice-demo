import {Component, OnInit} from '@angular/core';
import {Article} from '../model/article';
import {ArticleService} from '../article.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-article',
  templateUrl: './new-article.component.html'
})
export class NewArticleComponent implements OnInit {
  article: Article = new Article();
  constructor(private _service: ArticleService, private _router: Router) { }

  ngOnInit() {
  }

  create(article: Article) {
    this._service.save(article).subscribe(() => this._router.navigate(['/articles']));
  }
}
