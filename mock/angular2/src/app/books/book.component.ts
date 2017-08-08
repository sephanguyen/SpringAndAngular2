import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { BookService } from './service/book.service';
import { Book } from './model/book';
import {UploadFileService} from './service/upload-file.service'

@Component({
  moduleId: module.id,
  selector: 'app-book',
  templateUrl: 'book.component.html',
  styleUrls: ['book.component.css']
})

export class BookComponent implements OnInit {

  books: Book[] = [];
  private bookIndex: number;
  constructor(private bookService: BookService,  private router: Router,
              private uploadFileService : UploadFileService) { }

  ngOnInit(): void {
    this.geData();
    this.uploadFileService.progress$.subscribe(
      data => console.log('progress = '+ data)
    );
  }
  geData() :void {
    this.bookService.getBooks().subscribe(
      (books : Book[]) => this.books = books
    );
  }
  onEdit(idBook: number): void {
    this.router.navigate(['/books', idBook]);
  }

  oDelete(idBook: number): void {
    this.bookService.deleteBook(idBook).subscribe(
      (book : Book) => {
        //this.books.splice(this.books.indexOf(book), 1);
        this.books = this.books.filter(b =>b !== book);
      }
    );
    
  }

  onChange(event) {
       console.log('onChange');
         var files = event.srcElement.files;
        console.log(files);
           this.uploadFileService.makeFileRequest(files).subscribe(() => {
        console.log('sent');
        
     });
  }

}
