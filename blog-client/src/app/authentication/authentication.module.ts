import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Store} from '@ngrx/store';
import {LoginComponent} from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthenticatedGuard} from './guards/authenticated-guard.service';
import {UnauthenticatedGuard} from './guards/unauthenticated-guard.service';
import {AuthenticatedHttp, httpFactory} from './authenticated-http.service';
import {RequestOptions, XHRBackend} from '@angular/http';
import {IsAuthenticatedDirective} from './is-authenticated.directive';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  {component: LoginComponent, path: 'login', canActivate: [UnauthenticatedGuard]},
  {component: RegistrationComponent, path: 'register', canActivate: [UnauthenticatedGuard]}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    IsAuthenticatedDirective
  ],
  declarations: [LoginComponent, IsAuthenticatedDirective, RegistrationComponent],
  providers: [
    AuthenticationService,
    AuthenticatedGuard,
    UnauthenticatedGuard,
    {
      provide: AuthenticatedHttp,
      useFactory: httpFactory,
      deps: [XHRBackend, RequestOptions, Store]
    }
  ]
})
export class AuthenticationModule { }
