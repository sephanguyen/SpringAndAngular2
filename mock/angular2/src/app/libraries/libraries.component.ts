import { Component, OnInit } from '@angular/core';
import { Library } from 'app/libraries/model/library';
import { LibraryService } from 'app/libraries/service/library.service';
import { Router } from '@angular/router'

@Component({
  moduleId: module.id,
  selector: 'my-libraries',
  templateUrl: './libraries.component.html',
  styleUrls: ['./libraries.component.css']
})

export class LibrariesComponent implements OnInit {
  title = 'Tour of Libraries';
  libraries: Library[];
  // selectedLibrary: Library;

  constructor(
    private router: Router,
    private libraryService: LibraryService) { }

  getLibraries(): void {
    this.libraryService.getLibraries().then(libraries => this.libraries = libraries);
  }

  ngOnInit(): void {
    this.getLibraries();
  }

  // onSelect(library: Library): void {
  //   this.selectedLibrary = library;
  // }

  gotoDetail(library: Library): void {
    this.router.navigate(['/libraries', library.id])
  }

  gotoAddNew(): void {
    this.router.navigate(['/libraries/add']);
  }

  delete(library: Library): void {
    if (confirm("It will delete this record.\nDo you want to continue?")) {
      this.libraryService
        .delete(library.id)
        .then(() => {
          this.libraries = this.libraries.filter(h => h !== library);
          // if (this.selectedLibrary === library) { this.selectedLibrary = null; }
        });
    }
  }

  convert(mili: number): string {
    var date = new Date(mili);
    return (date.toISOString().slice(0, 10));
  }
}