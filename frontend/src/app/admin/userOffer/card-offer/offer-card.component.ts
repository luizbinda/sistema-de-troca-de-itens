import {Component, Input, OnInit} from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {ImageService} from "../../../services/image.service";
import {ConfirmationService} from "primeng";
import {OfferListModel} from "../../models/offerListModel";

@Component({
  selector: 'app-offer-card',
  templateUrl: './offer-card.component.html',
  styleUrls: ['./offer-card.component.scss'],
  providers: [ConfirmationService]
})
export class OfferCardComponent implements OnInit {

  @Input() offer: OfferListModel;

  constructor(private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
  }

    urlImage(photo: string): SafeResourceUrl {
      return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${photo}`);
    }
}
