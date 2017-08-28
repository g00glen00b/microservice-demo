import {FormControl, FormGroup} from '@angular/forms';
import {ProfileService} from '../../profiles/profile.service';

export class AuthenticationValidators {

  public static confirmation(group: FormGroup) {
    if (group.get('password').value == group.get('password2').value) {
      return null;
    } else {
      return { confirmation: true };
    }
  }

  public static uniqueUsername(service: ProfileService, debounce: number) {
    let timeout;
    return (control: FormControl) => {
      clearTimeout(timeout);
      return new Promise((resolve) => {
        timeout = setTimeout(() => {
          service
            .findOne(control.value)
            .subscribe(() => resolve({unique: true}), () => resolve(null));
        }, debounce);
      });
    };
  }

  public static validateEmail(control: FormControl) {
     if (EMAIL_REGEX.test(control.value)) {
       return null;
     } else {
       return { email: true };
     }
  }
}

export const EMAIL_REGEX: RegExp = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
