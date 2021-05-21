import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from '../../shared/shared.module';
import {ReactiveFormsModule} from "@angular/forms";
import {UserOfferRoutingModule} from "./user-offer-routing.module";
import {ItemModule} from "../item/item.module";
import {OfferCardComponent} from "./card-offer/offer-card.component";
import {OfferFormComponent} from "./offer-form/offer-form.component";
import {ItemCardComponent} from "./card-item/item-card.component";


@NgModule({
  declarations: [OfferCardComponent, OfferFormComponent, ItemCardComponent],
    imports: [
        CommonModule,
        UserOfferRoutingModule,
        SharedModule,
        ReactiveFormsModule,
        ItemModule
    ]
})
export class UserOfferModule { }
