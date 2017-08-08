import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs/Rx';
import { UserService } from 'app/users/service/user.service';
import { User } from 'app/users/model/user';
import 'rxjs/add/operator/switchMap';


@Component({
  moduleId: module.id,
  selector: 'user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css'],
})

export class UserDetailComponent implements OnInit {
  private subscription: Subscription;
  private isAddNew = true;
  user: User = new User();

  ngOnInit(): void {
    this.subscription = this.route.params.subscribe((params: any) => {
    document.getElementById("error_name_empty").style.display = "none";
    document.getElementById("error_pass_empty").style.display = "none";
    document.getElementById("error_roleid_empty").style.display = "none";
    document.getElementById("error_libid_empty").style.display = "none";
    document.getElementById("error_firstname_empty").style.display = "none";
    document.getElementById("error_lastname_empty").style.display = "none";
    document.getElementById("error_birthday_empty").style.display = "none";
    document.getElementById("error_address_empty").style.display = "none";
    document.getElementById("error_phone_empty").style.display = "none";
    document.getElementById("error_cardid_empty").style.display = "none";
     document.getElementById("error_email_empty").style.display = "none";
      if (params.hasOwnProperty('id')) {
        this.isAddNew = false;

        return this.userService.getUser(+params['id']).subscribe((user: User) => {
          var date = new Date(user.birthDay);
          this.user = user;
          this.user.birthDay = date.toISOString().substring(0, 10);;
        });
      }else {

        this.user = new User();
       this.user.birthDay = (new Date()).toISOString().slice(0, 10);
      }
      
    });

  }

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private location: Location,
  ) { }


  goBack(): void {
    this.location.back();
  }
  submit(): void {
    var data: any = this.user.userName;

     if (data == null || data == "") {
        document.getElementById("error_name_empty").style.display = "block";
        return;
    }document.getElementById("error_name_empty").style.display = "none";
    data = this.user.password;
    if (data == null || data == "") {
      document.getElementById("error_pass_empty").style.display = "block";
      return;
    }document.getElementById("error_pass_empty").style.display = "none";
    data = this.user.roleID;
    if (data == null || data == "") {
      document.getElementById("error_roleid_empty").style.display = "block";
      return;
    } document.getElementById("error_roleid_empty").style.display = "none";
    data = this.user.libID;
    if (data == null || data == "") {
      document.getElementById("error_libid_empty").style.display = "block";
      return;
    }document.getElementById("error_libid_empty").style.display = "none";
    data = this.user.firstName;
    if (data == null || data == "") {
      document.getElementById("error_firstname_empty").style.display = "block";
      return;
    } document.getElementById("error_firstname_empty").style.display = "none";
    data = this.user.lastName;
    if (data == null || data == "") {
      document.getElementById("error_lastname_empty").style.display = "block";
      return;
    }document.getElementById("error_lastname_empty").style.display = "none";
    data = this.user.address;
    if (data == null || data == "") {
      document.getElementById("error_address_empty").style.display = "block";
      return;
    }document.getElementById("error_address_empty").style.display = "none";
    data = this.user.birthDay;
    if (data == null || data == "" || new Date(this.user.birthDay) < new Date("1890-01-02")|| new Date(this.user.birthDay) > new Date("2050-01-02")) {
      document.getElementById("error_birthday_empty").style.display = "block";
      return;
    } document.getElementById("error_birthday_empty").style.display = "none";
    data = this.user.phone;
    var regexStr : any = "^[0-9]*$";
    if (data == null || data == "") {
      document.getElementById("error_phone_empty").style.display = "block";
      return;
    }document.getElementById("error_phone_empty").style.display = "none";
    var validations ={
    email: [/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/, 'Please enter a valid email address']
    };
    data = this.user.email;
    var regex = /^[\w-]+(\.[\w-]+)*@([a-z0-9-]+(\.[a-z0-9-]+)*?\.[a-z]{2,6}|(\d{1,3}\.){3}\d{1,3})(:\d{4})?$/i;
    if (data == null || data == "") {
      document.getElementById("error_email_empty").style.display = "block";
      return;
    }
    if(!(regex.exec(data) != null)){
      document.getElementById("error_email_empty").style.display = "block";return;
    }
    document.getElementById("error_email_empty").style.display = "none";
    data = this.user.cardID;
    if (data == null || data == "") {
      document.getElementById("error_cardid_empty").style.display = "block";
      return;
    }document.getElementById("error_cardid_empty").style.display = "none";
    if (this.isAddNew) {
      this.userService.createFromModel(this.user)
        .then(() => this.goBack());
    }
    else {
      this.userService.update(this.user)
        .then(() => this.goBack());
    }
  }

}
