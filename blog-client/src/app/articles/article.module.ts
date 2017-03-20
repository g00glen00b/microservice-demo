import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ArticleService} from './article.service';
import {ArticlesComponent} from './articles.component';
import {Routes, RouterModule} from '@angular/router';
import { ArticleExcerptComponent } from './article-excerpt/article-excerpt.component';
import {MarkdownService} from './markdown.service';

const routes: Routes = [
  {path: 'articles', component: ArticlesComponent}
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes)],
  declarations: [ArticlesComponent, ArticleExcerptComponent],
  providers: [ArticleService, MarkdownService]
})
export class ArticleModule {
}
