import { Component, OnInit } from '@angular/core';
import {ArticleService} from './article.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html'
})
export class ArticlesComponent implements OnInit {

  constructor(private _service: ArticleService) { }

  ngOnInit() {
  }

}
