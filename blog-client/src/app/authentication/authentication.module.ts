import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {INITIAL_STATE, Store} from '@ngrx/store';
import {LoginComponent} from './login/login.component';
import {RouterModule, Routes} from '@angular/router';
import {AuthenticationService} from './authentication.service';
import {FormsModule} from '@angular/forms';
import {AuthenticatedGuard} from './authenticated-guard.service';
import {UnauthenticatedGuard} from './unauthenticated-guard.service';
import {AuthenticatedHttp, httpFactory} from './authenticated-http.service';
import {RequestOptions, XHRBackend} from '@angular/http';

const routes: Routes = [
  {component: LoginComponent, path: 'login', canActivate: [UnauthenticatedGuard]}
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    FormsModule
  ],
  declarations: [LoginComponent],
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
