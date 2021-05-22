import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from '../../shared/shared.module';
import {ReactiveFormsModule} from "@angular/forms";
import {OfferViewRoutingModule} from "./offer-view-routing.module";
import {ItemModule} from "../item/item.module";
import {ItemCardComponent} from "./card-item/item-card.component";
import {OfferViewComponent} from "./offer-view/offer-view.component";


@NgModule({
  declarations: [ItemCardComponent, OfferViewComponent],
    imports: [
        CommonModule,
        OfferViewRoutingModule,
        SharedModule,
        ReactiveFormsModule,
        ItemModule
    ]
})
export class OfferViewModule { }
