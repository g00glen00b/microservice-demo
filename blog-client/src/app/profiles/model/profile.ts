import {baseUrl} from '../profile.service';
export class Profile {
  username: string;
  firstname: string;
  lastname: string;
  bio: string;
  avatar: string;


  constructor(username: string, firstname: string, lastname: string, bio: string, avatar: string) {
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.bio = bio;
    this.avatar = avatar;
  }

  static fromResponse(response) {
    return new Profile(response['username'], response['firstname'], response['lastname'], response['bio'], `${baseUrl}/${response['username']}/avatar`);
  }
}
