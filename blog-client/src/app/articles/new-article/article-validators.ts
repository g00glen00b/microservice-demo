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

  getUniqueSlugValidation(slug: string): Promise<IUniqueSlugValidationResult> {
    return new Promise<IUniqueSlugValidationResult>(resolve => {
      this._service
        .findOne(slug)
        .subscribe(() => resolve({ uniqueInvalid: true }), error => {
          if (error.status == 404) {
            resolve(null);
          } else {
            resolve({ uniqueInvalid: true });
          }
        });
    });
  }
}

export interface IUniqueSlugValidationResult {
  uniqueInvalid: boolean;
}
