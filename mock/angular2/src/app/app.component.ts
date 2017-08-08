import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Dashboard';

  constructor(
    private router: Router
  ) { }

  getURL(): string {
	let url = this.router.url;
	let arrStr = url.split(/[/]/);
    return arrStr[1];
  }
}