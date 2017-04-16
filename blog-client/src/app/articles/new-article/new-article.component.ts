import { Component, OnInit } from '@angular/core';
import {Article} from '../model/article';
import * as _ from 'lodash';
import {ArticleService} from '../article.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ArticleValidators} from './article-validators';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-article',
  templateUrl: './new-article.component.html'
})
export class NewArticleComponent implements OnInit {
  prefix: string;
  form: FormGroup;
  constructor(private _service: ArticleService, private _fb: FormBuilder, private _validators: ArticleValidators,
              private _router: Router) { }

  ngOnInit() {
    this.prefix = this.getUrlPrefix();
    this.form = this._fb.group({
      title: new FormControl('', Validators.required),
      slug: new FormControl('', Validators.required, this._validators.uniqueSlug.bind(this._validators)),
      text: new FormControl('')
    });
  }

  onSubmit(article: Article) {
    this._service.save(article).subscribe(() => this._router.navigate(['/articles']));
  }

  createSlug(form: FormGroup) {
    console.log(form);
    const slug = form.get('slug');
    const title = form.get('title');
    if (slug.value == null || slug.value.length == 0) {
      slug.setValue(_.kebabCase(title.value));
    }
  }

  getUrlPrefix() {
    return window.location.protocol + '//' +
      window.location.hostname +
      (_.isEmpty(window.location.port) ? '' : ':' + window.location.port) +
      '/articles/';
  }
}
