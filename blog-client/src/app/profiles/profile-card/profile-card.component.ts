import {Component, Input, OnInit} from '@angular/core';
import {Profile} from '../model/profile';

@Component({
  selector: 'app-profile-card',
  templateUrl: './profile-card.component.html'
})
export class ProfileCardComponent implements OnInit {
  @Input() profile: Profile;

  constructor() { }

  ngOnInit() {
  }

}
