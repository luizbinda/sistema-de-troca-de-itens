import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LOCALE_ID, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { environment } from '../environments/environment';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { PageNotificationModule,  ErrorStackModule } from '@nuvem/primeng-components';
import { ErrorModule, SecurityModule } from '@nuvem/angular-base';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { BlockUIModule } from 'ng-block-ui';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from './shared/shared.module';
import { TokenInterceptor } from 'src/interceptors/token.interceptor';
import { UserComponent } from './user/user.component';
import {InputTextModule} from 'primeng/inputtext';

@NgModule({
    declarations: [
        AppComponent,
        DiarioErrosComponent,
        LoginComponent,
        UserComponent
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
        ErrorStackModule,
        ErrorModule,
        SharedModule,
        SecurityModule.forRoot(environment.auth),
        InputTextModule,
        PageNotificationModule        
    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        { provide: LOCALE_ID, useValue: 'pt-BR'},
        { provide: HTTP_INTERCEPTORS, multi: true, useClass: TokenInterceptor}
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
