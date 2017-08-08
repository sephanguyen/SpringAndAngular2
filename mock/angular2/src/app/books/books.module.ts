import { NgModule } from '@angular/core';
import { CommonModule, DatePipe }   from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule }    from '@angular/forms';

import { BookService }    from './service/book.service';
import { BookComponent} from './book.component';
import { bookRouting} from './book-routing.module';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { BookSearchComponent } from './book-search/book-search.component';
import { UploadFileService }    from './service/upload-file.service';
import { Ng2DatetimePickerModule } from 'ng2-datetime-picker';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        bookRouting,
        FormsModule,
        Ng2DatetimePickerModule
    ],
    exports: [BookSearchComponent],
    declarations: [
        BookComponent, 
        BookDetailComponent, BookSearchComponent
    ],
    providers: [BookService, UploadFileService, DatePipe],
})
export class BooksModule { }
