import { Component } from '@angular/core';
import { AppComponent } from '../../app.component';
import { Authentication, User } from '@nuvem/angular-base';
import { AdminComponent } from 'src/app/admin/admin.component';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/admin/models/userModel';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopbarComponent {

    users: UserModel = new UserModel();

    constructor(
        public app: AdminComponent, 
        private readonly _authentication: Authentication<User>,
        private router: Router
    ) {
    }

    ngOnInit(): void {
        this.findUser();
    }

    findUser(){
        this.users = JSON.parse(localStorage.getItem('user'));
    }

    get usuario() {
        return this._authentication.getUser();
    }

    isAuthenticated() {
        return this._authentication.isAuthenticated();
    }

    logout(){
        localStorage.clear(); 
        this.router.navigate(['../login']);
    }

    teste(){
        console.log("teste");
        this.router.navigate(['../user']);
    }
}
