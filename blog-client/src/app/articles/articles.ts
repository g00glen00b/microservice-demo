import {Article} from './article';
export class Articles {
  offset: number;
  limit: number;
  totalResults: number;
  results: Article[];


  constructor(offset: number, limit: number, totalResults: number, results: Article[]) {
    this.offset = offset;
    this.limit = limit;
    this.totalResults = totalResults;
    this.results = results;
  }

  static fromResponse(response): Articles {
    let results = response['results'].map(Article.fromResponse(response));
    return new Articles(response['offset'], response['limit'], response['totalResults'], results);
  }
}
