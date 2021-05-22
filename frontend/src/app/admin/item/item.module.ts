import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ItemRoutingModule } from './item-routing.module';
import { ItemIndexComponent } from './item-index/item-index.component';
import {SharedModule} from '../../shared/shared.module';
import {ReactiveFormsModule} from "@angular/forms";
import {ItemCardComponent} from "./card-item/item-card.component";
import {ItemFormComponent} from "./item-form/item-form.component";
import {FormImageComponent} from "./form-image/form-image.component";


@NgModule({
    declarations: [ItemIndexComponent, ItemCardComponent, ItemFormComponent, FormImageComponent],
    imports: [
        CommonModule,
        ItemRoutingModule,
        SharedModule,
        ReactiveFormsModule
    ]
})
export class ItemModule { }
