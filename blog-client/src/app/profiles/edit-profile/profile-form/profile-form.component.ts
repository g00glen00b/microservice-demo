import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {Profile} from '../../model/profile';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-profile-form',
  styleUrls: ['./profile-form.component.css'],
  templateUrl: './profile-form.component.html'
})
export class ProfileFormComponent implements OnInit, OnChanges {
  @Input() profile: Profile;
  @Output() save: EventEmitter<Profile> = new EventEmitter<Profile>();
  @Output() saveAvatar: EventEmitter<File> = new EventEmitter<File>();
  form: FormGroup;

  constructor(private _fb: FormBuilder) { }

  ngOnInit() {
      this.form = this._fb.group({
        username: new FormControl('', [Validators.required, Validators.maxLength(32)]),
        firstname: new FormControl('', [Validators.maxLength(16)]),
        lastname: new FormControl('', [Validators.maxLength(16)]),
        bio: new FormControl('', [Validators.maxLength(256)])
      });
      this.updateFormValues(this.profile);
  }

  ngOnChanges() {
    if (this.form != null) {
      this.updateFormValues(this.profile);
    }
  }

  uploadAvatar(event) {
    if (event.length > 0) {
      this.saveAvatar.emit(event[0]);
    }
  }

  onSubmit(profile: Profile) {
    this.save.emit(new Profile(this.profile.username, profile.firstname, profile.lastname, profile.bio, profile.avatar));
  }

  updateFormValues(profile: Profile) {
    this.form.get('username').setValue(profile == null ? '' : profile.username);
    this.form.get('firstname').setValue(profile == null ? '' : profile.firstname);
    this.form.get('lastname').setValue(profile == null ? '' : profile.lastname);
    this.form.get('bio').setValue(profile == null ? '' : profile.bio);
    this.form.get('username').disable();
  }

}
