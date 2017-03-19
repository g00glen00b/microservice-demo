import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {NavbarComponent} from './navbar/navbar.component';
import {ClarityModule} from 'clarity-angular';

@NgModule({
  imports: [
    CommonModule,
    ClarityModule.forChild(),
  ],
  exports: [NavbarComponent],
  declarations: [NavbarComponent]
})
export class SharedModule { }
