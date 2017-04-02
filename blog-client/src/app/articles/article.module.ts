import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ArticleService} from './article.service';
import {ArticlesComponent} from './articles.component';
import {RouterModule, Routes} from '@angular/router';
import {ArticleExcerptComponent} from './article-excerpt/article-excerpt.component';
import {MarkdownService} from './markdown.service';
import {SharedModule} from '../shared/shared.module';
import {ArticleDetailComponent} from './article-detail/article-detail.component';
import {MarkdownModule} from 'angular2-markdown';
import {ClarityModule} from 'clarity-angular';
import { ArticleNewComponent } from './article-new/article-new.component';
import {AuthenticatedGuardService} from '../authentication/authenticated-guard.service';

const routes: Routes = [
  {path: 'articles', component: ArticlesComponent},
  {path: 'articles/new', component: ArticleNewComponent, canActivate: [AuthenticatedGuardService]},
  {path: 'articles/:slug', component: ArticleDetailComponent}
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes), SharedModule, MarkdownModule.forRoot(), ClarityModule.forChild()],
  declarations: [ArticlesComponent, ArticleExcerptComponent, ArticleDetailComponent, ArticleNewComponent],
  providers: [ArticleService, MarkdownService]
})
export class ArticleModule {
}
