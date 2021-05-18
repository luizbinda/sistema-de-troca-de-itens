import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppTopbarComponent } from '../components/topbar/app.topbar.component';
import { AppFooterComponent } from '../components/footer/app.footer.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { SharedModule } from '../shared/shared.module';
import { BreadcrumbModule, MenuModule } from '@nuvem/primeng-components';
import { VersionTagModule } from '@nuvem/angular-base';


@NgModule({
  declarations: [
    AdminComponent,
    AppTopbarComponent,
    AppFooterComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule,
    BreadcrumbModule,
    MenuModule,
    VersionTagModule
  ]
})
export class AdminModule { }