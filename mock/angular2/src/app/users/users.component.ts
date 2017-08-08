import { Component, OnInit } from '@angular/core';
import { User } from 'app/users/model/user';
import { UserService } from 'app/users/service/user.service';
import { Router } from '@angular/router'

@Component({
  moduleId: module.id,
  selector: 'my-users',
  templateUrl: './users.component.html',
  styleUrls: [ './users.component.css' ]
})

export class UsersComponent implements OnInit {
  title = 'Tour of Users';
  users: User[];
  //selectedUser: User;
  constructor(
    private router: Router,
    private userService: UserService) { }
  getUsers(): void {
    this.userService.getUsers().then(users => this.users = users);
  }
  ngOnInit(): void {
    this.getUsers();
  }
  //onSelect(user: User): void {
  //  this.selectedUser = user;
  //}

  gotoDetail(user: User): void {
    this.router.navigate(['/users', user.id])
  }

  gotoNewUser(): void {
    this.router.navigate(['/users/add'])
  }

 /*add(username: string , password : string,roleid:string,libid:string,
  lastname:string, firstname:string,birthday: string,address: string ,phone: string,
  email: string, cardid: string, active: boolean ): void {
    username = username.trim();
    password = password.trim();
    roleid = roleid.trim();
    libid = libid.trim();
    lastname = lastname.trim();
    firstname = firstname.trim();
    birthday = birthday.trim();
    address = address.trim();
    phone = phone.trim();
    email = email.trim();
    cardid = cardid.trim();
    if (!username) { return; }
    this.userService.create(username,password,roleid,libid,lastname,firstname,birthday,address,phone,email,cardid,active)
      .then(user => {
        this.users.push(user);
        //this.selectedUser = null;
      });
  }*/

  delete(user: User): void {
    this.userService
        .delete(user.id)
        .then(() => {
          this.users = this.users.filter(u => u !== user);
          //if (this.selectedUser === user) { this.selectedUser = null; }
        });
  }  

}