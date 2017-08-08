import { AngularHeroesCliPage } from './app.po';

describe('angular-heroes-cli App', () => {
  let page: AngularHeroesCliPage;

  beforeEach(() => {
    page = new AngularHeroesCliPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
