import { TestBed, inject } from '@angular/core/testing';

import { UsersComponent } from './users.component';

describe('a users component', () => {
	let component: UsersComponent;

	// register all needed dependencies
	beforeEach(() => {
		TestBed.configureTestingModule({
			providers: [
				UsersComponent
			]
		});
	});

	// instantiation through framework injection
	beforeEach(inject([UsersComponent], (UsersComponent) => {
		component = UsersComponent;
	}));

	it('should have an instance', () => {
		expect(component).toBeDefined();
	});
});