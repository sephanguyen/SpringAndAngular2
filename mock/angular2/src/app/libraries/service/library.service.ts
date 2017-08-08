import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx'; // test Observable
import 'rxjs/add/operator/toPromise';

import { Library } from 'app/libraries/model/library';


@Injectable()
export class LibraryService {

	private librariesUrl = 'api/libraries';  // URL to web api

	constructor(private http: Http) { }

	getLibraries(): Promise<Library[]> {
		return this.http.get(this.librariesUrl)
			.toPromise()
			// .then(response => response.json().data as Library[])
			.then(response => response.json() as Library[])
			.catch(this.handleError);
	}

	private handleError(error: any): Promise<any> {
		console.error('An error occurred', error); // for demo purposes only
		return Promise.reject(error.message || error);
	}

	getLibraryObs(id: number): Observable<Library> { // test Observable
		const url = `${this.librariesUrl}/${id}`;
		return this.http.get(url)
			.map((response: Response) => response.json())
			.catch(this.handleError);
	}

	getLibrary(id: number): Promise<Library> {
		const url = `${this.librariesUrl}/${id}`;
		return this.http.get(url)
			.toPromise()
			// .then(response => response.json().data as Library)
			.then(response => response.json() as Library)
			.catch(this.handleError);
	}

	private headers = new Headers({ 'Content-Type': 'application/json' });

	update(library: Library): Promise<Library> {
		const url = `${this.librariesUrl}/${library.id}`;
		return this.http
			.put(url, JSON.stringify(library), { headers: this.headers })
			.toPromise()
			.then(() => library)
			.catch(this.handleError);
	}



	create(library: Library): Promise<Library> {
		return this.http
			.post(this.librariesUrl, JSON.stringify({
				libAdmin: library.libAdmin, createDate: library.createDate,
				active: library.active
			}), { headers: this.headers })
			.toPromise()
			.then(res => res.json() as Library)
			.catch(this.handleError);
	}

	delete(id: number): Promise<void> {
		const url = `${this.librariesUrl}/${id}`;
		return this.http.delete(url, { headers: this.headers })
			.toPromise()
			.then(() => null)
			.catch(this.handleError);
	}


}
