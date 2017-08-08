import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';
import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';
// Observable class extensions
import 'rxjs/add/observable/of';
// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { LibrarySearchService } from 'app/libraries/service/library-search.service';
import { Library } from 'app/libraries/model/library';

@Component({
  moduleId: module.id,
  selector: 'library-search',
  templateUrl: './library-search.component.html',
  styleUrls: [ './library-search.component.css' ],
  providers: [LibrarySearchService]
})

export class LibrarySearchComponent implements OnInit {
  resultShow: boolean;
  libraries: Observable<Library[]>;
  private searchTerms = new Subject<string>();
  constructor(
    private librarySearchService: LibrarySearchService,
    private router: Router) {}
  // Push a search term into the observable stream.
  search(term: string): void {
    this.resultShow = true;
    this.searchTerms.next(term);
  }
  ngOnInit(): void {
    this.libraries = this.searchTerms
      .debounceTime(300)        // wait 300ms after each keystroke before considering the term
      .distinctUntilChanged()   // ignore if next search term is same as previous
      .switchMap(term => term   // switch to new observable each time the term changes
        // return the http search observable
        ? this.librarySearchService.search(term)
        // or the observable of empty libraries if there was no search term
        : Observable.of<Library[]>([]))
      .catch(error => {
        // TODO: add real error handling
        console.log(error);
        return Observable.of<Library[]>([]);
      });
  }
  gotoDetail(library: Library): void {
    this.resultShow = false;
    let link = ['/libraries', library.id];
    this.router.navigate(link);
  }
}
