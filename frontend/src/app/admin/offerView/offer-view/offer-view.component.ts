import { Component, OnInit } from '@angular/core';
import {ItemService} from "../../../services/item.service";
import {UserModel} from "../../models/userModel";
import {ActivatedRoute, Router} from "@angular/router";
import {PageNotificationService} from "@nuvem/primeng-components";
import {Constants} from "../../../shared/Constants";
import {OfferService} from "../../../services/offer.service";
import {OfferListModel} from "../../models/offerListModel";
import {getLoggedUser} from "../../../shared/getLogged";
import {ConfirmationService} from "primeng";

@Component({
  selector: 'app-offer-form',
  templateUrl: './offer-view.component.html',
  styleUrls: ['./offer-view.component.scss'],
    providers: [ConfirmationService]
})
export class OfferViewComponent implements OnInit {

    offerId:  number;
    offer:  OfferListModel;
    user: UserModel = getLoggedUser();

    constructor(
        private itemService: ItemService,
        private offerService: OfferService,
        private route: ActivatedRoute,
        private router: Router,
        private notification: PageNotificationService,
        private confirmationService: ConfirmationService,
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
            },() => this.notification.addErrorMessage(Constants.SAVED_ERROR),
            () => this.router.navigate(['/admin/dashboard'])
        );
    }

    refuseOffer() {
        this.confirmationService.confirm({
            message: Constants.REFUSE_OFFER,
            header: 'Confirmation',
            icon: 'ui-icon-warning',
            accept: () => {
                this.offerService.refuse(this.offerId).subscribe(
                    () => this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY),
                    () => this.notification.addErrorMessage(Constants.SAVED_ERROR),
                    () => this.router.navigate(['/admin/dashboard'])
                );
            }
        });
    }

}
