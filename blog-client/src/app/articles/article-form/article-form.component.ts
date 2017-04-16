import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ArticleValidators} from './article-validators';
import {Article} from '../model/article';
import * as _ from 'lodash';

@Component({
  selector: 'app-article-form',
  templateUrl: './article-form.component.html'
})
export class ArticleFormComponent implements OnInit, OnChanges {
  prefix: string;
  form: FormGroup;
  @Input() article: Article;
  @Output() save: EventEmitter<Article> = new EventEmitter<Article>();
  constructor(private _fb: FormBuilder, private _validators: ArticleValidators) { }

  ngOnInit() {
    this.prefix = this.getUrlPrefix();
  }

  ngOnChanges() {
    this.form = this._fb.group({
      title: new FormControl(this.article == null ? '' : this.article.title, Validators.required),
      slug: new FormControl(this.article == null ? '' : this.article.slug, Validators.required, this._validators.uniqueSlug.bind(this._validators)),
      text: new FormControl(this.article == null ? '' : this.article.text)
    });
  }

  onSubmit(article: Article) {
    this.save.emit(article);
  }

  createSlug(form: FormGroup) {
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
