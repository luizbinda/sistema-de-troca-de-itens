import { Component, OnInit } from '@angular/core';
import {UserService} from '../../services/user.service';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-user-index',
  templateUrl: './user-index.component.html',
  styleUrls: ['./user-index.component.css']
})
export class UserIndexComponent implements OnInit {

  users: any[] = [];
  form: FormGroup;
  displayModal = false;
  constructor(
      private userService: UserService,
      private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
      this.getAllUsers();
      this.initForm();
  }

  initForm() {
      this.form = this.formBuilder.group({
          birthDate : [] ,
          name : [] ,
          email : [] ,
          cpf : [] ,
      });
  }

  getAllUsers() {
      this.userService.index().subscribe( users => this.users = users);
  }

    showModalDialog() {
        this.displayModal = true;
    }

    saveUSer() {
        this.displayModal = false;
        // this.userService.store(this.form.value);
    }

}
