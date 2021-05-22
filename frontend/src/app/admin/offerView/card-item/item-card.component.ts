import {Component, Input, OnInit} from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {ImageService} from "../../../services/image.service";
import {ConfirmationService} from "primeng";

@Component({
  selector: 'app-item-card',
  templateUrl: './item-card.component.html',
  styleUrls: ['./item-card.component.scss'],
  providers: [ConfirmationService]
})
export class ItemCardComponent implements OnInit {

  @Input() item: ItemModel;

  constructor(
      private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
  }

    urlImage(photo: string): SafeResourceUrl {
      return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${photo}`);
    }
}
