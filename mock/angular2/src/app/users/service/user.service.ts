import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import { Observable } from 'rxjs/Rx';

import { User } from 'app/users/model/user';

@Injectable()
export class UserService {

	private usersUrl = 'api/users';  // URL to web api

	constructor(private http: Http) { }

	getUsers(): Promise<User[]> {
		return this.http.get(this.usersUrl)
			.toPromise()
			// .then(response => response.json().data as User[])
			.then(response => response.json() as User[])
			.catch(this.handleError);
	}

	private handleError(error: any): Promise<any> {
		console.error('An error occurred', error); // for demo purposes only
		return Promise.reject(error.message || error);
	}

	getUser(id: number): Observable<User> {
		const url = `${this.usersUrl}/${id}`;
		return this.http.get(url)
			.map((response: Response) => response.json())
			.catch(this.handleError);
	}

	private headers = new Headers({ 'Content-Type': 'application/json' });

	update(user: User): Promise<User> {
		const url = `${this.usersUrl}/${user.id}`;
		return this.http
			.put(url, JSON.stringify(user), { headers: this.headers })
			.toPromise()
			.then(() => user)
			.catch(this.handleError);
	}

	/*create(userName: string, password: string, roleID: string, libID: string,
		lastName: string, firstName: string, birthDay: string, address: string, phone: string,
		email: string, cardID: string, active: boolean): Promise<User> {
		return this.http
			.post(this.usersUrl, JSON.stringify({
				userName: userName, password: password, roleID: roleID,
				libID: libID, lastName: lastName, firstName: firstName, birthDay: birthDay,
				address: address, phone: phone, email: email, cardID: cardID, active: active
			}), { headers: this.headers })
			.toPromise()
			// .then(res => res.json().data)
			.then(res => res.json() as User)
			.catch(this.handleError);
	}*/

	createFromModel(user: User): Promise<User> {
		return this.http
			.post(this.usersUrl, JSON.stringify({
				userName: user.userName, password: user.password, roleID: user.roleID,
				libID: user.libID, lastName: user.lastName, firstName: user.firstName, birthDay: user.birthDay,
				address: user.address, phone: user.phone, email: user.email, cardID: user.cardID, active: user.active
			}), { headers: this.headers })
			.toPromise()
			// .then(res => res.json().data)
			.then(res => res.json() as User)
			.catch(this.handleError);
	}

	delete(id: number): Promise<void> {
		const url = `${this.usersUrl}/${id}`;
		return this.http.delete(url, { headers: this.headers })
			.toPromise()
			.then(() => null)
			.catch(this.handleError);
	}


}
