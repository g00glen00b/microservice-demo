import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NavbarComponent} from './navbar/navbar.component';
import {ClarityModule} from 'clarity-angular';
import {RouterModule} from '@angular/router';

@NgModule({
  imports: [CommonModule, ClarityModule.forChild(), RouterModule],
  exports: [NavbarComponent],
  declarations: [NavbarComponent]
})
export class SharedModule { }
