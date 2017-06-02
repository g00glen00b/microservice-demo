import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfileCardComponent } from './profile-card/profile-card.component';
import {ProfileService} from './profile.service';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import {Route, RouterModule} from '@angular/router';
import { ProfileFormComponent } from './edit-profile/profile-form/profile-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SharedModule} from '../shared/shared.module';
import {AuthenticatedGuard} from '../authentication/guards/authenticated-guard.service';

const routes: Route[] = [
  { path: 'profile/edit', component: EditProfileComponent, canActivate: [AuthenticatedGuard] }
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes), FormsModule, ReactiveFormsModule, SharedModule],
  providers: [ProfileService],
  declarations: [ProfileCardComponent, EditProfileComponent, ProfileFormComponent],
  exports: [ProfileCardComponent]
})
export class ProfileModule { }
