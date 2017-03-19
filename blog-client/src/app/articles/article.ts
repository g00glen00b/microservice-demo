import * as _ from 'lodash';

const excerptLength: number = 100;

export class Article {
  id: number;
  title: string;
  text: string;
  username: string;
  slug: string;
  created: Date;


  constructor(id: number, title: string, text: string, username: string, slug: string, created: Date) {
    this.id = id;
    this.title = title;
    this.text = text;
    this.username = username;
    this.slug = slug;
    this.created = created;
  }

  static fromResponse(response): Article {
    return new Article(response['id'], response['title'], response['text'], response['username'], response['slug'], new Date(response['create']));
  }

  getExcerpt() {
    return _.truncate(this.text, {length: excerptLength});
  }
}
