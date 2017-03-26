import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NavbarComponent} from './navbar/navbar.component';
import {ClarityModule} from 'clarity-angular';
import {RouterModule} from '@angular/router';
import { PaginationComponent } from './pagination/pagination.component';

@NgModule({
  imports: [CommonModule, ClarityModule.forChild(), RouterModule],
  exports: [NavbarComponent, PaginationComponent],
  declarations: [NavbarComponent, PaginationComponent]
})
export class SharedModule { }
