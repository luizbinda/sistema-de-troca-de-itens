import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {ImageModel} from "../../models/ImageModel";
import {ImageService} from "../../../services/image.service";
import {PageNotificationService} from "@nuvem/primeng-components";
import {Constants} from "../../../shared/Constants";
import {ConfirmationService} from "primeng";

@Component({
  selector: 'app-item-card',
  templateUrl: './item-card.component.html',
  styleUrls: ['./item-card.component.scss'],
  providers: [ConfirmationService]
})
export class ItemCardComponent implements OnInit {

  @Input() item: ItemModel;
  @Output() editItem = new EventEmitter<ItemModel>();
  @Output() getItens = new EventEmitter<void>();
  displayModalImage = false;
  imageEdit: ImageModel = new ImageModel();

  constructor(
      private sanitizer: DomSanitizer,
      private imageService: ImageService,
      private notification: PageNotificationService,
      private confirmationService: ConfirmationService
      ) {}

  ngOnInit(): void {
  }

    urlImage(photo: string): SafeResourceUrl {
      return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${photo}`);
    }

    emitEditItem() {
        this.editItem.emit(this.item);
    }

    emitGetItems() {
        this.getItens.emit();
    }

    deleteImage(id: number) {
        this.confirmationService.confirm({
            message: Constants.DELETE_IMAGE,
            header: 'Confirmation',
            icon: 'ui-icon-warning',
            accept: () => {
                this.imageService.delete(id).subscribe(
                    () => {
                        this.notification.addSuccessMessage(Constants.DELETE_IMAGE_SUCCESSFULY);
                        this.emitGetItems();
                    }
                )
            }
        });

    }

    showModalDialog(value: boolean) {
        this.displayModalImage = value;
    }

    showModalDialogEdite(image: ImageModel) {
        this.imageEdit = image;
        this.displayModalImage = true;
    }



}
