import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';
import { Observable }     from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { User }           from 'app/users/model/user';
	
@Injectable()
export class UserSearchService {
  constructor(private http: Http) {}
  search(term: string): Observable<User[]> {
    return this.http
               .get(`app/users/?username=${term}`)
               // .map(response => response.json().data as User[]);
               .map(response => response.json() as User[]);
  }
}
