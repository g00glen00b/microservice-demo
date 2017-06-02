import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {PasswordValidators} from './password-validators';
import {ProfileService} from '../../profiles/profile.service';

@Component({
  selector: 'app-registration',
  styleUrls: ['./registration.component.css'],
  templateUrl: './registration.component.html'
})
export class RegistrationComponent implements OnInit {
  form: FormGroup;

  constructor(private _fb: FormBuilder, private _profileService: ProfileService) { }

  ngOnInit() {
    this.form = this._fb.group({
      username: new FormControl('',
        [Validators.required, Validators.maxLength(64)],
        [PasswordValidators.uniqueUsername(this._profileService, 300)]),
      password: new FormGroup({
        password: new FormControl('', [Validators.required, Validators.minLength(6)]),
        password2: new FormControl('', [Validators.required, Validators.minLength(6)])
      }, PasswordValidators.confirmation)
    });
  }

  isUsernameInvalid(): boolean {
    const field = this.form.get('username');
    return field.invalid && (field.dirty || field.touched);
  }

  isPasswordInvalid(): boolean {
    const field = this.form.get('password').get('password');
    return field.invalid && (field.dirty || field.touched);
  }

  isPasswordConfirmationInvalid(): boolean {
    const group = this.form.get('password'),
          field = group.get('password2');
    return (group.invalid && !this.isPasswordInvalid()) && (field.dirty || field.touched);
  }

  isPasswordTooShort(): boolean {
    const errors = this.form.get('password').get('password').errors;
    return errors != null && (errors['required'] || errors['minlength']);
  }

  isPasswordNotConfirmed(): boolean {
    const errors = this.form.get('password').errors;
    return errors != null && errors['confirmation'];
  }

}
