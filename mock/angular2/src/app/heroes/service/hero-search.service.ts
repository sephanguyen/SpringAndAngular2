import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Hero }           from 'app/heroes/model/hero';
	
@Injectable()
export class HeroSearchService {
  constructor(private http: Http) {}
  search(term: string): Observable<Hero[]> {
    return this.http
               .get(`http://58bcdc23a0cc651200a4be62.mockapi.io/api/heroes/?name=${term}`)
               // .map(response => response.json().data as Hero[]);
               .map(response => response.json() as Hero[]);
  }
}
