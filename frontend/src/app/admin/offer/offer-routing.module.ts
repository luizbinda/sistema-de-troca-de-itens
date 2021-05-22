import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OfferFormComponent} from "./offer-form/offer-form.component";

const routes: Routes = [
    { path: '', component: OfferFormComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OfferRoutingModule { }
