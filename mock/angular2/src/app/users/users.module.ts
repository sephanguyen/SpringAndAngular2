import { NgModule } from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { UsersComponent } from './users.component';
import { UsersRoutingModule } from './users-routing.module';
import { UserDetailComponent } from 'app/users/user-detail/user-detail.component';
import { UserService }         from 'app/users/service/user.service';
import { UserSearchComponent } from 'app/users/user-search/user-search.component'

@NgModule({
    imports: [
        UsersRoutingModule,
        CommonModule,
        FormsModule
    ],
    exports: [UserSearchComponent],
    declarations: [
        UsersComponent,
        UserDetailComponent,
        UserSearchComponent
    ],
    providers: [UserService],
})
export class UsersModule { }
