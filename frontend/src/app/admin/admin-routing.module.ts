import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin.component';


const routes: Routes = [
  {
    path: '', component: AdminComponent, children: [
      { path: '', redirectTo: '', pathMatch: 'full' },
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
