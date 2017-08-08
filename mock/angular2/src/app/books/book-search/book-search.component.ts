import { Component, OnInit, Input, Output } from '@angular/core';
import { Router }            from '@angular/router';
import { Observable }        from 'rxjs/Observable';
import { Subject }           from 'rxjs/Subject';
// Observable class extensions
import 'rxjs/add/observable/of';
// Observable operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

import { Book } from '../model/book';
import { BookService } from '../service/book.service';

@Component({
  moduleId: module.id,
  selector: 'book-search',
  templateUrl: 'book-search.component.html',
  styleUrls: [ 'book-search.component.css' ]
})
export class BookSearchComponent implements OnInit {

  resultShow: boolean;
  books: Observable<Book[]>;
  private searchTerms = new Subject<string>();
  constructor(
    private bookService: BookService,
    private router: Router) {}
  // Push a search term into the observable stream.
  search(term: string): void {
    this.resultShow = true;
    this.searchTerms.next(term);
  }
  ngOnInit(): void {
    this.books = this.searchTerms
      .debounceTime(300)        
      .distinctUntilChanged()   
      .switchMap(term => term   
        
        ? this.bookService.search(term) 
        : Observable.of<Book[]>([]))
      .catch(error => {
       
        console.log(error);
        return Observable.of<Book[]>([]);
      });
  }


  gotoDetail(book: Book): void {
    this.resultShow = false;
    let link = ['/books', book.id];
    this.router.navigate(link);
  }

}
