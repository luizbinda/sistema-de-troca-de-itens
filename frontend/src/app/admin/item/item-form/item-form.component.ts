import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { PageNotificationService } from '@nuvem/primeng-components';
import { finalize } from 'rxjs/operators';
import {ItemService} from "../../../services/item.service";
import {Constants} from "../../../shared/Constants";
import {categories} from "../../../shared/Categories";
import {UserModel} from "../../models/userModel";

@Component({
  selector: 'app-item-form',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.css']
})
export class ItemFormComponent implements OnInit {

    form: FormGroup;
    isEditing: boolean = false;
    categories = categories.map( category => ({value: category.id, label: category.description}));
    user : UserModel = new UserModel(1)
    @Output() displayModal = new EventEmitter<boolean>();
    @Output() updateItens = new EventEmitter<boolean>();

    constructor(
      private itemService: ItemService,
      private formBuilder: FormBuilder,
      private notification: PageNotificationService
    ) {}

    ngOnInit(): void {
      this.initForm();
    }

    emitDisplayModal(value: boolean) {
        this.displayModal.emit(value);
    }

    initForm() {
      this.form = this.formBuilder.group({
          name : [null, Validators.required],
          description : [null, Validators.required],
          categoryId : [null, Validators.required],
          userId : [this.user.id, Validators.required],
          available : [true],
          id : []
      });
    }

    saveUSer() {
        if(this.form.value.id){
            this.itemService.update(this.form.value).pipe(
                finalize(() => {
                    this.emitDisplayModal(true);
                })
            ).subscribe(
                () => {
                    this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
                },() => {
                    this.notification.addErrorMessage("Falha ao Atualizar");
                }
            )
        }else{
            this.itemService.store(this.form.value).pipe(
                finalize(() =>{
                    this.emitDisplayModal(false)
                } )
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

    deleteUser(id){
        console.log(id);
    }

    editUser(id){
        this.isEditing = true;
        this.itemService.show(id).subscribe(
            (user) => {
                this.form.patchValue({
                    ...user
                })
            }
        )
    }

}
