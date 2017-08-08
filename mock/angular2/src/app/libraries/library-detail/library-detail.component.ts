import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';
import { Subscription } from 'rxjs/Rx';
import { LibraryService } from 'app/libraries/service/library.service';
import { Library } from 'app/libraries/model/library';

import 'rxjs/add/operator/switchMap';


@Component({
  moduleId: module.id,
  selector: 'library-detail',
  templateUrl: './library-detail.component.html',
  styleUrls: ['./library-detail.component.css'],
})

export class LibraryDetailComponent implements OnInit {
  private subscription: Subscription;
  private isAddNew: boolean = true;
  private library: Library;

  ngOnInit(): void {


    this.subscription = this.route.params.subscribe((params: any) => {
      if (params.hasOwnProperty('id')) {
        this.isAddNew = false;

        this.libraryService.getLibraryObs(+params['id']).subscribe((library: Library) => {
          var date = new Date(library.createDate);
          this.library = library;
          this.library.createDate = date.toISOString().slice(0, 10);
          // toISOString 2017-03-13T08:55:05.053Z
          // toUTCString Mon, 13 Mar 2017 08:55:05 GMT
          // toDateString Mon Mar 13 2017
        });
      } else {
        this.isAddNew = true;
        this.library = new Library();
        this.library.createDate = (new Date()).toISOString().slice(0, 10);
      }
    });
  }



  constructor(
    private libraryService: LibraryService,
    private route: ActivatedRoute,
    private router: Router
  ) { }



  goBack(): void {
    // this.location.back();
    this.router.navigate(['/libraries']);
  }

  save(): void {

    var data: any = this.library.libAdmin;
    var date: any = this.library.createDate;
    var isOK: boolean = true;

    if (data == null || data == "") {
      document.getElementById("error_name_empty").style.display = "block";
      isOK = false;
    } else {
      document.getElementById("error_name_empty").style.display = "none";
    }

    if ((new Date(date) < new Date("1970-01-01")) || (new Date(date) > new Date("2050-01-01"))) {
      document.getElementById("error_date_invalid").style.display = "block";
      isOK = false;
    } else {
      document.getElementById("error_date_invalid").style.display = "none";
    }

    if (!isOK) {
      return;
    }

    if (this.isAddNew) {
      this.libraryService.create(this.library)
        .then(() => this.goBack());
    } else {
      if (confirm("It will overwrite the old data.\nDo you want to continue?")) {
        this.libraryService.update(this.library)
          .then(() => this.goBack());
      }
    }
  }

  convert(mili: number): string {
    var date = new Date(mili);
    return (date.toLocaleString());
  }
}

