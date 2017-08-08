import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BookComponent } from './book.component';
import { BookDetailComponent } from './book-detail/book-detail.component';

const BOOK_ROUTES: Routes = [
    { path: 'books', component: BookComponent },
    { path: 'books/new', component: BookDetailComponent },
    { path: 'books/:id', component: BookDetailComponent }
    
];


export const bookRouting = RouterModule.forChild(BOOK_ROUTES);