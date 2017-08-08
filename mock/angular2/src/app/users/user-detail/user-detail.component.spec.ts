import { TestBed, inject } from '@angular/core/testing';

import { UserDetailComponent } from './user-detail.component';

describe('a user-detail component', () => {
	let component: UserDetailComponent;

	// register all needed dependencies
	beforeEach(() => {
		TestBed.configureTestingModule({
			providers: [
				UserDetailComponent
			]
		});
	});

	// instantiation through framework injection
	beforeEach(inject([UserDetailComponent], (UserDetailComponent) => {
		component = UserDetailComponent;
	}));

	it('should have an instance', () => {
		expect(component).toBeDefined();
	});
});