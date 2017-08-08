import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LibrariesComponent } from './libraries.component';
import { LibraryDetailComponent } from './library-detail/library-detail.component';

const routes: Routes = [
  { path: 'libraries', component: LibrariesComponent },
  { path: 'libraries/add', component: LibraryDetailComponent },
  { path: 'libraries/:id', component: LibraryDetailComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LibrariesRoutingModule { }