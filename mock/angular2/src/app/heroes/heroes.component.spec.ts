import { TestBed, inject } from '@angular/core/testing';

import { HeroesComponent } from './heroes.component';

describe('a heroes component', () => {
	let component: HeroesComponent;

	// register all needed dependencies
	beforeEach(() => {
		TestBed.configureTestingModule({
			providers: [
				HeroesComponent
			]
		});
	});

	// instantiation through framework injection
	beforeEach(inject([HeroesComponent], (HeroesComponent) => {
		component = HeroesComponent;
	}));

	it('should have an instance', () => {
		expect(component).toBeDefined();
	});
});