import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {StoreModule} from '@ngrx/store';
import {auth} from './auth.reducer';
import {LoginComponent} from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {FormsModule} from '@angular/forms';
import {AuthenticatedGuardService} from './authenticated-guard.service';
import {UnauthenticatedGuardService} from './unauthenticated-guard.service';

const routes: Routes = [
  {component: LoginComponent, path: 'login', canActivate: [UnauthenticatedGuardService]}
];

@NgModule({
  imports: [
    CommonModule,
    StoreModule.provideStore({ auth }),
    RouterModule.forChild(routes),
    FormsModule
  ],
  declarations: [LoginComponent],
  providers: [AuthenticationService, AuthenticatedGuardService, UnauthenticatedGuardService]
})
export class AuthenticationModule { }
