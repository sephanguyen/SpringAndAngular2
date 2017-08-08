import { NgModule } from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { LibrariesComponent } from './libraries.component';
import { LibrariesRoutingModule } from './libraries-routing.module';
import { LibraryDetailComponent } from 'app/libraries/library-detail/library-detail.component';
import { LibraryService }         from 'app/libraries/service/library.service';
import { LibrarySearchComponent } from 'app/libraries/library-search/library-search.component'

@NgModule({
    imports: [
        LibrariesRoutingModule,
        CommonModule,
        FormsModule
    ],
    exports: [LibrarySearchComponent],
    declarations: [
        LibrariesComponent,
        LibraryDetailComponent,
        LibrarySearchComponent
    ],
    providers: [LibraryService],
})
export class LibrariesModule { }
