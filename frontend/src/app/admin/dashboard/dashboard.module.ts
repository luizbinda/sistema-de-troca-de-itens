import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {SharedModule} from '../../shared/shared.module';
import {ReactiveFormsModule} from "@angular/forms";
import {OfferIndexComponent} from "./offer-index/offer-index.component";
import {DashboardRoutingModule} from "./dashboard-routing.module";
import {ItemModule} from "../item/item.module";
import {ItemCardComponent} from "./card-item/item-card.component";

@NgModule({
  declarations: [OfferIndexComponent, ItemCardComponent],
    imports: [
        CommonModule,
        DashboardRoutingModule,
        SharedModule,
        ReactiveFormsModule,
        ItemModule
    ]
})
export class DashboardModule { }
