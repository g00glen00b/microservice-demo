import { BlogClientPage } from './app.po';

describe('blog-client App', function() {
  let page: BlogClientPage;

  beforeEach(() => {
    page = new BlogClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
