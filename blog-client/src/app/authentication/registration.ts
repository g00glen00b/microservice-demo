export class Registration {
  id: number;
  email: string;
  username: string;
  created: Date;


  constructor(id: number, email: string, username: string, created: Date) {
    this.id = id;
    this.email = email;
    this.username = username;
    this.created = created;
  }

  static fromResponse(response): Registration {
    return new Registration(response['id'], response['email'], response['username'], new Date(response['created']));
  }
}
