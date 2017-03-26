import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ArticleService} from './article.service';
import {ArticlesComponent} from './articles.component';
import {Routes, RouterModule} from '@angular/router';
import {ArticleExcerptComponent} from './article-excerpt/article-excerpt.component';
import {MarkdownService} from './markdown.service';
import {SharedModule} from '../shared/shared.module';
import {ArticleDetailComponent} from './article-detail/article-detail.component';
import {MarkdownModule} from 'angular2-markdown';

const routes: Routes = [
  {path: 'articles', component: ArticlesComponent},
  {path: 'articles/:slug', component: ArticleDetailComponent}
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes), SharedModule, MarkdownModule.forRoot()],
  declarations: [ArticlesComponent, ArticleExcerptComponent, ArticleDetailComponent],
  providers: [ArticleService, MarkdownService]
})
export class ArticleModule {
}
