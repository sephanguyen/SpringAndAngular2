import { TestBed, inject } from '@angular/core/testing';

import { HeroDetailComponent } from './hero-detail.component';

describe('a hero-detail component', () => {
	let component: HeroDetailComponent;

	// register all needed dependencies
	beforeEach(() => {
		TestBed.configureTestingModule({
			providers: [
				HeroDetailComponent
			]
		});
	});

	// instantiation through framework injection
	beforeEach(inject([HeroDetailComponent], (HeroDetailComponent) => {
		component = HeroDetailComponent;
	}));

	it('should have an instance', () => {
		expect(component).toBeDefined();
	});
});