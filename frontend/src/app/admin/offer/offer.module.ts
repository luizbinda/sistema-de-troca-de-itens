import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from '../../shared/shared.module';
import {ReactiveFormsModule} from "@angular/forms";
import {OfferRoutingModule} from "./offer-routing.module";
import {ItemModule} from "../item/item.module";
import {ItemCardComponent} from "./card-item/item-card.component";
import {OfferFormComponent} from "./offer-form/offer-form.component";


@NgModule({
  declarations: [ItemCardComponent, OfferFormComponent],
    imports: [
        CommonModule,
        OfferRoutingModule,
        SharedModule,
        ReactiveFormsModule,
        ItemModule
    ]
})
export class OfferModule { }
