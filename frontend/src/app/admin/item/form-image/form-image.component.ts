import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserModel} from "../../models/userModel";
import {PageNotificationService} from "@nuvem/primeng-components";
import {finalize} from "rxjs/operators";
import {Constants} from "../../../shared/Constants";
import {ImageModel} from "../../models/ImageModel";
import {ImageService} from "../../../services/image.service";
import {ItemModel} from "../../models/itemModel";

@Component({
  selector: 'app-form-image',
  templateUrl: './form-image.component.html',
  styleUrls: ['./form-image.component.scss']
})
export class FormImageComponent implements OnInit, OnChanges {

    @Output() displayModalImage = new EventEmitter<boolean>();
    @Output() updateImages = new EventEmitter<void>();
    @Input() imageEdit: ImageModel;
    @Input() item: ItemModel;

    form: FormGroup;
    uploadedFiles: any[] = [];
    user : UserModel = new UserModel(1);

    constructor(
        private imageService: ImageService,
        private formBuilder: FormBuilder,
        private notification: PageNotificationService,
    ) {}

    ngOnInit(): void {
        this.initForm();
    }

    ngOnChanges(): void {
        if (this.imageEdit.id) {
            this.form.patchValue({
                ...this.imageEdit,
            })
        }
    }

    emitDisplayModal(value: boolean) {
        if (!value) {
            this.form.reset();
        }
        this.displayModalImage.emit(value);
    }

    private emitUpdateItems() {
        this.updateImages.emit();
    }

    private initForm() {
        this.form = this.formBuilder.group({
            photo : [null, Validators.required],
            description : [null, Validators.required],
            itemId : [this.item.id, Validators.required],
            id : []
        });
    }

    onUpload(event) {
        console.log(event)
        if (event.originalEvent.target.files.length > 0) {
            const file = event.originalEvent.target.files[0];
            this.form.get('photo').setValue(file);
        }
    }

    submit() {
        const formData = new FormData();
        formData.append('photo', this.form.get('photo').value);
        formData.append('description', this.form.get('description').value);
        formData.append('itemId', this.form.get('itemId').value);
        this.saveImage(formData);
    }

    private submitedFinished() {
        this.emitDisplayModal(false);
        this.emitUpdateItems();
    }

    saveImage(form: FormData) {
        if(this.form.value.id){
            this.imageService.update(form).pipe(finalize(() => this.submitedFinished()))
                .subscribe(
                    () => {
                        this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
                    },() => {
                        this.notification.addErrorMessage(Constants.SAVED_ERROR);
                    }
                )
        }else{
            this.imageService.store(form).pipe(finalize(() => this.submitedFinished()))
                .subscribe(
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


}
