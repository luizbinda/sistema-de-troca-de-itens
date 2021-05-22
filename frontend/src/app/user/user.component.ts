import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { FormBuilder, FormControl, FormGroup } from "@angular/forms";
import { PageNotificationService } from '@nuvem/primeng-components';
import { finalize } from 'rxjs/operators';
import { UserModel } from '../admin/models/userModel';
import { Router } from '@angular/router';
import {Constants} from "../shared/Constants";

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

    users: any[] = [];
    form: FormGroup;
    isEditing: boolean = false;
    userLog: UserModel = new UserModel();

    text: string;

    disabled: boolean = true;   

    constructor(
        private userService: UserService,
        private formBuilder: FormBuilder,
        private notification: PageNotificationService,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.initForm();
        this.getUser();
    }

    toggleDisabled() {
        this.disabled = !this.disabled;
    }

    getId(): boolean {
        this.userLog = JSON.parse(localStorage.getItem('user'));

        if (this.userLog != null && this.userLog.id != null) {
            return true;
        }  
        return false 
    }

    initForm() {
        this.form = this.formBuilder.group({
            birthDate: [],
            name: [],
            email: [],
            cpf: [],
            id: []
        });
    }

    getUser() {
        this.userLog = JSON.parse(localStorage.getItem('user'));

        if (this.userLog != null && this.userLog.id != null) {
            this.form.patchValue({...this.userLog, birthDate: new Date(this.userLog.birthDate)});
        }   
    }

    setUser() {
        localStorage.setItem('user', JSON.stringify(this.userLog));
    }

    closeModalDialog() {
        this.form.reset();
        this.isEditing = false;
    }

    saveUser() {
        if (this.form.value.id) {
            this.userService.update(this.form.value).pipe(
                finalize(() => {
                    this.router.navigate(['../admin']);
                }
                )
            ).subscribe(
                () => {
                    this.updateLocalStorage(this.form.value);
                    this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
                }, () => {
                    this.notification.addErrorMessage(Constants.SAVED_ERROR);
                }
            )
        } else {
            this.userService.store(this.form.value).pipe(
                finalize(() => {
                    this.router.navigate(['../admin']); 
                })
            ).subscribe(
                () => {
                    this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
                },
                () => {
                    this.notification.addErrorMessage(Constants.SAVED_ERROR);
                }
            )
        }
    }

    updateLocalStorage(user){
        this.userLog = user;
        this.setUser();
    }

}
