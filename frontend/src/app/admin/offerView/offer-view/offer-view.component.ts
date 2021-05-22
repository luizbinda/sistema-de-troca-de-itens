import { Component, OnInit } from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {ItemService} from "../../../services/item.service";
import {UserModel} from "../../models/userModel";
import {ActivatedRoute, NavigationExtras, Router} from "@angular/router";
import {PageNotificationService} from "@nuvem/primeng-components";
import {Constants} from "../../../shared/Constants";
import {OfferService} from "../../../services/offer.service";
import {OfferModel} from "../../models/offerModel";
import {OfferListModel} from "../../models/offerListModel";

@Component({
  selector: 'app-offer-form',
  templateUrl: './offer-view.component.html',
  styleUrls: ['./offer-view.component.scss']
})
export class OfferViewComponent implements OnInit {

    offerId:  number;
    offer:  OfferListModel;
    user: UserModel = new UserModel(2) ;

    constructor(
        private itemService: ItemService,
        private offerService: OfferService,
        private route: ActivatedRoute,
        private router: Router,
        private notification: PageNotificationService
    ) {
        this.route.queryParams.subscribe(params => {
            if (params && params.offerId) {
                this.offerId = JSON.parse(params.offerId);
                this.findOffer(this.offerId);
            } else {
                this.notification.addErrorMessage(Constants.REDIRECT_ERROR);
                this.router.navigate(['/admin/dashboard']);
            }
        });
    }

    ngOnInit(): void {
    }

    findOffer(offerId: number) {
        this.offerService.show(offerId).subscribe( offer => this.offer = offer);
    }

    acceptOffer() {
        this.offerService.accept(this.offerId).subscribe(
            () => {
                this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
            },() => {
                this.notification.addErrorMessage(Constants.SAVED_ERROR);
            }
        );
    }

    refuseOffer() {
        this.offerService.refuse(this.offerId).subscribe(
            () => {
                this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
            },() => {
                this.notification.addErrorMessage(Constants.SAVED_ERROR);
            }
        );
    }

}
