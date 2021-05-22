import {Component, Input, OnInit} from '@angular/core';
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

  constructor() {}

  ngOnInit(): void {
  }
}
