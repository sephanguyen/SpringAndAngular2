import { Injectable, EventEmitter } from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';

import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { Book } from '../model/book';

@Injectable()
export class BookService {

   private bookUrl = 'http://58bcdc23a0cc651200a4be62.mockapi.io/api/book'; 

 
  constructor(private http: Http) { }

  getBooks(): Observable<Book[]> {
    return this.http.get(this.bookUrl)
                    .map((res : Response) => res.json())
                    .catch(this.handleError);
  }

  
    


  getBook(id: number): Observable<Book> {
    return this.http.get(`${this.bookUrl}/${id}`) 
                         .map((res:Response) => 
                            res.json()
                          )
                         .catch(this.handleError); 
  }

  deleteBook(id : number): Observable<Book>{
      return this.http.delete(`${this.bookUrl}/${id}`) 
                         .map((res:Response) =>  {
                            res.json();
                          })
                         .catch(this.handleError); 
    
  }

  
  
  editBook(body: Object) : Observable<Book>{
  let bodyString = JSON.stringify(body); 
        let headers      = new Headers({ 'Content-Type': 'application/json' }); 
        let options       = new RequestOptions({ headers: headers }); 

        return this.http.put(`${this.bookUrl}/${body['id']}`, bodyString, options) 
                         .map((res:Response) =>  {
                            res.json();
                          })
                         .catch(this.handleError); 
  }

  addBook (book: Book): Observable<Book> {
        let bodyString = JSON.stringify(book); 
        let headers      = new Headers({ 'Content-Type': 'application/json' }); 
        let options       = new RequestOptions({ headers: headers }); 

        return this.http.post(this.bookUrl, bodyString, options) 
                         .map((res:Response) =>  {
                            res.json();
                          }) 
                         .catch(this.handleError); 
    }  

    search(term: string): Observable<Book[]> {
     return this.http
          .get(this.bookUrl + "?name=" + term)
          .map(response => response.json() as Book[]);
  }


  private handleError(error: any): Observable<any> {
		console.error('An error occurred', error); // for demo purposes only
    console.log(error)
		return Observable.throw(error.json().error || 'Server error');
	} 
}
