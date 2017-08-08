import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params }   from '@angular/router';
import { Location }                 from '@angular/common';

import { HeroService } from 'app/heroes/service/hero.service';
import { Hero } from 'app/heroes/model/hero';
import 'rxjs/add/operator/switchMap';


@Component({
  moduleId: module.id,
  selector: 'hero-detail',
  templateUrl: './hero-detail.component.html',
  styleUrls: [ './hero-detail.component.css' ],
})

export class HeroDetailComponent implements OnInit {

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.heroService.getHero(+params['id']))
      .subscribe(hero => this.hero = hero);
  }

  constructor(
    private heroService: HeroService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

	hero: Hero;

  goBack(): void {
    // this.location.back();
    this.router.navigate(['/heroes']);
  }

  save(): void {
    this.heroService.update(this.hero)
      .then(() => this.goBack());
  }

}
