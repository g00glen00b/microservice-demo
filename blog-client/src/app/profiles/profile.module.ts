import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileCardComponent } from './profile-card/profile-card.component';
import {ProfileService} from './profile.service';

@NgModule({
  imports: [CommonModule],
  providers: [ProfileService],
  declarations: [ProfileCardComponent],
  exports: [ProfileCardComponent]
})
export class ProfileModule { }
