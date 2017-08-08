import { TestBed, inject } from '@angular/core/testing';

import { LibrariesComponent } from './libraries.component';

describe('a libraries component', () => {
	let component: LibrariesComponent;

	// register all needed dependencies
	beforeEach(() => {
		TestBed.configureTestingModule({
			providers: [
				LibrariesComponent
			]
		});
	});

	// instantiation through framework injection
	beforeEach(inject([LibrariesComponent], (LibrariesComponent) => {
		component = LibrariesComponent;
	}));

	it('should have an instance', () => {
		expect(component).toBeDefined();
	});
});