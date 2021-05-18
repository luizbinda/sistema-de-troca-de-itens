import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LOCALE_ID, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { environment } from '../environments/environment';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PageNotificationModule, BreadcrumbModule, MenuModule, ErrorStackModule } from '@nuvem/primeng-components';
import { ErrorModule, SecurityModule, VersionTagModule } from '@nuvem/angular-base';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { BlockUIModule } from 'ng-block-ui';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from './shared/shared.module';
import { TokenInterceptor } from 'src/interceptors/token.interceptor';

@NgModule({
    declarations: [
        AppComponent,
        DiarioErrosComponent,
        LoginComponent
    ],
    imports: [
        BlockUIModule.forRoot({
            message: 'Carregando...'
          }),
        ReactiveFormsModule,  
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        HttpClientModule,
        PageNotificationModule,
        ErrorStackModule,
        ErrorModule,
        SharedModule,
        SecurityModule.forRoot(environment.auth)
    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        { provide: LOCALE_ID, useValue: 'pt-BR'},
        { provide: HTTP_INTERCEPTORS, multi: true, useClass: TokenInterceptor}
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }