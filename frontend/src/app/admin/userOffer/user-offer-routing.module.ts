import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserOfferComponent} from "./user-offer/user-offer.component";

const routes: Routes = [
    { path: '', component: UserOfferComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserOfferRoutingModule { }
