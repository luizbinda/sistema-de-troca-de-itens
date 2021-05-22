import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OfferViewComponent} from "./offer-view/offer-view.component";

const routes: Routes = [
    { path: '', component: OfferViewComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OfferViewRoutingModule { }
