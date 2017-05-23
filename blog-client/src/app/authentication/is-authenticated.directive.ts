import {Directive, OnInit, TemplateRef, ViewContainerRef} from '@angular/core';
import {Store} from '@ngrx/store';
import {AppState} from '../shared/app-state';
import {Authentication} from './authentication';

@Directive({
  selector: '[appIsAuthenticated]'
})
export class IsAuthenticatedDirective implements OnInit {

  constructor(private templateRef: TemplateRef<any>, private viewContainer: ViewContainerRef, private _store: Store<AppState>) {
  }

  ngOnInit(): void {
    this._store
      .select(state => state.auth)
      .subscribe(auth => this.renderElementOnAuthenticated(auth));
  }

  renderElementOnAuthenticated(auth: Authentication) {
    if (auth.token != null) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }

}
