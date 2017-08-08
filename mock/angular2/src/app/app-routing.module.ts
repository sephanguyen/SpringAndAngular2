import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: '', redirectTo: '/heroes', pathMatch: 'full' },
  { path: '', redirectTo: '/users', pathMatch: 'full' },
  { path: '', redirectTo: '/libraries', pathMatch: 'full' },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})

export class AppRoutingModule {}
