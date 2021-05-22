import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PageNotificationService } from '@nuvem/primeng-components';
import { LoginService } from '../services/login.service';
import {Constants} from "../shared/Constants";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: FormGroup;
  submit = false;


  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private notification: PageNotificationService  
  ) { }

  iniciarForm(){
    this.form = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, []]
    })
  }

  ngOnInit(): void {
    this.iniciarForm();
  }

  login(){
    this.submit = true;

    this.loginService.login(this.form.value).subscribe( user => {
      localStorage.setItem('token', user.cpf);
      localStorage.setItem('user', JSON.stringify(user));  
      this.router.navigate(['../admin']);
    }, erro => {
      this.notification.addErrorMessage(Constants.LOGIN_ERROR);
      localStorage.clear(); 
    } );
    // this.router.navigate(['admin']);
  }

}
