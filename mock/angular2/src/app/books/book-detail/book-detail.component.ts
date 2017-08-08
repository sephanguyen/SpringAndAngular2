import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { Observable } from 'rxjs/Rx';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

import {
  FormArray,
  FormGroup,
  FormControl,
  Validators,
  FormBuilder
} from '@angular/forms';

import { Book } from '../model/book';
import { BookService } from '../service/book.service';
import { EmitterService } from '../service/emitter.service';

@Component({
  moduleId: module.id,
  selector: 'app-book-detail',
  templateUrl: 'book-detail.component.html',
  styleUrls: ['book-detail.component.css']
})
export class BookDetailComponent implements OnInit, OnDestroy {

  bookForm: FormGroup;
  book: Book;
  private bookId: number;
  private subscription: Subscription;
  private isNew = true;
  listId: string;
  constructor(private bookService: BookService,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private datePipe: DatePipe) { }

  ngOnInit(): void {

    this.subscription = this.route.params.subscribe(
      (params: any) => {
        if (params.hasOwnProperty('id')) {
          this.isNew = false;
          this.bookId = +params["id"];

          this.bookService.getBook(this.bookId).subscribe(
            (book: Book) => {
              this.book = book;
              this.initForm();
            },
            err => console.log(err)
          );
        } else {
          this.isNew = true;
          this.book = null;
          this.initForm();
        }

      }
    );

  }


  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  initForm(): void {    
    let bookName = '';
    let bookAuthor = '';
    let bookPublish = '';
    let bookYearPublished;
    let bookPageCount = 0;
    let bookPrice = 0;
    let bookDateArrived;
    let bookBorrowed = 0;
    let bookRating = 0;
    let bookActive = true; 
    if (!this.isNew) {
      bookName = this.book.name;
      bookAuthor = this.book.author;
      bookPublish = this.book.publish;
      bookYearPublished = this.book.yearPublished;
      bookPageCount = this.book.pageCount;
      bookPrice = this.book.price;
      bookDateArrived = this.book.dateArrived;
      bookBorrowed = this.book.borrowed;
      bookRating = this.book.rating;
      bookActive = this.book.active;
    }
    this.bookForm = this.formBuilder.group({
      id: [ this.bookId],
      name: [bookName, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(100)])],
      author: [bookAuthor, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(100)])],
      publish: [bookPublish, Validators.compose([Validators.required, Validators.minLength(5), Validators.maxLength(100)])],
      yearPublished: [this.datePipe.transform(bookYearPublished, 'yyyy-MM-dd'), Validators.required],
      pageCount: [bookPageCount, Validators.required],
      price: [bookPrice, Validators.required],
      dateArrived: [this.datePipe.transform(bookDateArrived, 'yyyy-MM-dd')],
      borrowed: [bookBorrowed],
      rating: [bookRating],
      active: [bookActive]
    });
  }

  onCancel(): void {
    this.navigateBack();
  }

  onSubmit(value: any): void {
    let bookOperation: Observable<Book>;

    const newBook = this.bookForm.value;
    if (this.isNew) {
      bookOperation = this.bookService.addBook(newBook);
    } else {
      bookOperation = this.bookService.editBook(newBook);
    }
    bookOperation.subscribe(
      book => {
        EmitterService.get(this.listId).emit(book);
        this.navigateBack();
      }
    )
   
  }
  navigateBack(): void {
     this.router.navigate(['/books']);
  }
}
