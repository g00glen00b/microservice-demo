import {FormControl, FormGroup} from '@angular/forms';
import {ProfileService} from '../../profiles/profile.service';

export class PasswordValidators {

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
}
