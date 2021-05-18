import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ItemIndexComponent} from './item-index/item-index.component';

const routes: Routes = [
    { path: '', component: ItemIndexComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemRoutingModule { }
