import { TestBed, inject } from '@angular/core/testing';

import { LibraryDetailComponent } from './library-detail.component';

describe('a library-detail component', () => {
	let component: LibraryDetailComponent;

	// register all needed dependencies
	beforeEach(() => {
		TestBed.configureTestingModule({
			providers: [
				LibraryDetailComponent
			]
		});
	});

	// instantiation through framework injection
	beforeEach(inject([LibraryDetailComponent], (LibraryDetailComponent) => {
		component = LibraryDetailComponent;
	}));

	it('should have an instance', () => {
		expect(component).toBeDefined();
	});
});