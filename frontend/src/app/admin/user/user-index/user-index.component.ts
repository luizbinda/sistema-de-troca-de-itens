import { Component, OnInit } from '@angular/core';
import {UserService} from '../../../services/user.service';
import {FormBuilder, FormGroup} from "@angular/forms";
import { PageNotificationService } from '@nuvem/primeng-components';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-user-index',
  templateUrl: './user-index.component.html',
  styleUrls: ['./user-index.component.css']
})
export class UserIndexComponent implements OnInit {

  users: any[] = [];
  form: FormGroup;
  displayModal = false;
  isEditing: boolean= false;

  constructor(
      private userService: UserService,
      private formBuilder: FormBuilder,
      private notification: PageNotificationService
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
          id : []
      });
  }

  getAllUsers() {
      this.userService.index().subscribe( users => this.users = users);
  }

    showModalDialog() {
        this.displayModal = true;
    }

    closeModalDialog() {
        this.form.reset();
        this.displayModal = false;
        this.isEditing = false;
    }

    saveUSer() {
        if(this.form.value.id){
            this.userService.update(this.form.value).pipe(
                finalize(() => {
                    this.closeModalDialog();
                }
                )
            ).subscribe(
                () => {
                    this.getAllUsers();
                    this.notification.addSuccessMessage("Atualizado com Sucesso");
                },() => {
                    this.notification.addErrorMessage("Falha ao Atualizar");
                }
            )
        }else{
            this.userService.store(this.form.value).pipe(
                finalize(() =>{
                    this.closeModalDialog()
                } )
            ).subscribe(
                () => {
                    this.getAllUsers();
                    this.notification.addSuccessMessage("Salvo com Sucesso");
                },
                () => {
                    this.notification.addErrorMessage("Erro ao salvar");
                }
            )
        }
    }

    editUser(id){
        this.isEditing = true;
        this.userService.show(id).subscribe(
            (user) => {
                this.displayModal = true;
                this.form.patchValue({
                    ...user,
                    birthDate: new Date(user.birthDate)
                })
            }
        )
    }

}
