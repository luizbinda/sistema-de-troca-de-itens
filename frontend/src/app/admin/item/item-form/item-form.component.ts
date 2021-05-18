import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { PageNotificationService } from '@nuvem/primeng-components';
import { finalize } from 'rxjs/operators';
import {ItemService} from "../../../services/item.service";
import {Constants} from "../../../shared/Constants";
import {categories} from "../../../shared/Categories";
import {UserModel} from "../../models/userModel";
import {ItemModel} from "../../models/itemModel";

@Component({
  selector: 'app-item-form',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.css']
})
export class ItemFormComponent implements OnInit, OnChanges, OnDestroy {

    form: FormGroup;
    isEditing: boolean = false;
    submited: boolean = false;
    categories = categories.map( category => ({value: category.id, label: category.description}));
    user : UserModel = new UserModel(1)
    @Output() displayModal = new EventEmitter<boolean>();
    @Output() updateItens = new EventEmitter<void>();
    @Input() itemEdit: ItemModel;

    constructor(
      private itemService: ItemService,
      private formBuilder: FormBuilder,
      private notification: PageNotificationService
    ) {}

    ngOnInit(): void {
      this.initForm();
    }

    ngOnChanges(): void {
        this.form.reset();
        if (this.itemEdit.id) {
            this.form.patchValue({
                ...this.itemEdit,
                categoryId: this.itemEdit.category.id,
                userId: this.itemEdit.user.id
            })
        }
    }

    ngOnDestroy() {
        console.log("teste")
    }

    emitDisplayModal(value: boolean) {
        if (!value) {
            this.form.reset();
        }
        this.displayModal.emit(value);
    }

    private emitUpdateItems() {
        this.updateItens.emit();
    }

    private initForm() {
      this.form = this.formBuilder.group({
          name : [null, Validators.required],
          description : [null, Validators.required],
          categoryId : [null, Validators.required],
          userId : [this.user.id, Validators.required],
          available : [true],
          id : []
      });
    }

    private submitedFinished() {
        this.emitDisplayModal(false);
        this.emitUpdateItems();
        this.submited = false;
    }

    saveUser() {
        this.submited = true;
        if(this.form.value.id){
            this.itemService.update(this.form.value).pipe(finalize(() => this.submitedFinished()))
                .subscribe(
                () => {
                    // this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
                },() => {
                    this.notification.addErrorMessage("Falha ao Atualizar");
                }
            )
        }else{
            this.itemService.store(this.form.value).pipe(finalize(() => this.submitedFinished()))
                .subscribe(
                () => {
                    // this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
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

}
