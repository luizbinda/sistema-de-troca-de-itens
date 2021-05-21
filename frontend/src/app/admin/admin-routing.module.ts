import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin.component';


const routes: Routes = [
  {
    path: '', component: AdminComponent, children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', loadChildren: () => import('../admin/dashboard/dashboard.module').then(m => m.DashboardModule) },
      { path: 'offer', loadChildren: () => import('../admin/offer/offer.module').then(m => m.OfferModule) },
      { path: 'users', loadChildren: () => import('../admin/user/user.module').then(m => m.UserModule) },
      { path: 'items', loadChildren: () => import('../admin/item/item.module').then(m => m.ItemModule) }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
