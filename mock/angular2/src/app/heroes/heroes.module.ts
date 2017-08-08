import { NgModule } from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { HeroesComponent } from './heroes.component';
import { HeroesRoutingModule } from './heroes-routing.module';
import { HeroDetailComponent } from 'app/heroes/hero-detail/hero-detail.component';
import { HeroService }         from 'app/heroes/service/hero.service';
import { HeroSearchComponent } from 'app/heroes/hero-search/hero-search.component'

@NgModule({
    imports: [
        HeroesRoutingModule,
        CommonModule,
        FormsModule
    ],
    exports: [HeroSearchComponent],
    declarations: [
        HeroesComponent,
        HeroDetailComponent,
        HeroSearchComponent
    ],
    providers: [HeroService],
})
export class HeroesModule { }
