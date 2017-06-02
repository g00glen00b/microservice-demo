import {AbstractControl} from '@angular/forms';
import {ArticleService} from '../article.service';
import {Injectable} from '@angular/core';

import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Injectable()
export class ArticleValidators {

  constructor(private _service: ArticleService) {
  }

  uniqueSlug(c: AbstractControl): { [key: string]: any; } {
    return this.getUniqueSlugValidation(c.value);
  }

  getUniqueSlugValidation(debounce: number) {
    let timeout;
    return (slug: string) => {
      clearTimeout(timeout);
      return new Promise<IUniqueSlugValidationResult>(resolve => {
        timeout = setTimeout(() => {
          this._service
            .findOne(slug)
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

export interface IUniqueSlugValidationResult {
  uniqueInvalid: boolean;
}
