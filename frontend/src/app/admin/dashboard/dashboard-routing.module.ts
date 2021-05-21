import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OfferIndexComponent} from "./offer-index/offer-index.component";

const routes: Routes = [
    { path: '', component: OfferIndexComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
