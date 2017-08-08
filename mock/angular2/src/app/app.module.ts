import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule }    from '@angular/http';

// Imports for loading & configuring the in-memory web api
// import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
// import { InMemoryDataService }  from './in-memory-data.service';

import { AppComponent }        from './app.component';

import { AppRoutingModule }     from './app-routing.module';
import { HeroesModule } from './heroes/heroes.module';
import { UsersModule } from './users/users.module';
import { BooksModule } from './books/books.module';
import { LibrariesModule } from './libraries/libraries.module';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HeroesModule,
    UsersModule,
    BooksModule,
    LibrariesModule,
    AppRoutingModule,
    HttpModule,
    // InMemoryWebApiModule.forRoot(InMemoryDataService),
  ],
  declarations: [

    AppComponent
  ],
  providers: [ ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
}
