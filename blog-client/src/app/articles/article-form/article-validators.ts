import {AbstractControl, FormControl} from '@angular/forms';
import {ArticleService} from '../article.service';
import {Injectable} from '@angular/core';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

export class ArticleValidators {

  public static uniqueSlug(service: ArticleService, debounce: number) {
    let timeout;
    return (control: FormControl) => {
      clearTimeout(timeout);
      return new Promise((resolve) => {
        timeout = setTimeout(() => {
          service
            .findOne(control.value)
            .subscribe(() => resolve({uniqueInvalid: true}), error => {
              if (error.status == 404) {
                resolve(null);
              } else {
                resolve({uniqueInvalid: true});
              }
            });
        }, debounce);
      });
    };
  }
}
