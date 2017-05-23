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
  @Input() title: string;
  @Output() save: EventEmitter<Article> = new EventEmitter<Article>();
  constructor(private _fb: FormBuilder, private _validators: ArticleValidators) { }

  ngOnInit() {
    this.prefix = this.getUrlPrefix();
    this.form = this._fb.group({
      title: new FormControl('', [Validators.required, Validators.maxLength(64)]),
      slug: new FormControl('', [Validators.required, Validators.maxLength(64)], this._validators.uniqueSlug.bind(this._validators)),
      text: new FormControl('', Validators.maxLength(16384))
    });
    this.updateFormValues(this.article);
  }

  ngOnChanges() {
    if (this.form != null) {
      this.updateFormValues(this.article);
    }
  }

  updateFormValues(article: Article) {
    this.form.get('title').setValue(article == null ? '' : article.title);
    this.form.get('slug').setValue(article == null ? '' : article.slug);
    this.form.get('text').setValue(article == null ? '' : article.text);
    if (article != null && article.id != null) {
      this.form.get('slug').disable();
    } else {
      this.form.get('slug').enable();
    }
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
