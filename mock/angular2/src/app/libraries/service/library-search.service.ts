import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Library }           from 'app/libraries/model/library';
	
@Injectable()
export class LibrarySearchService {
  constructor(private http: Http) {}
  search(term: string): Observable<Library[]> {
    return this.http
               .get(`app/libraries/?libAdmin=${term}`)
               // .map(response => response.json().data as Library[]);
               .map(response => response.json() as Library[]);
  }
}
