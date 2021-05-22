import { Component, OnInit } from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {ItemService} from "../../../services/item.service";
import {UserModel} from "../../models/userModel";
import {ActivatedRoute, NavigationExtras, Router} from "@angular/router";
import {OfferService} from "../../../services/offer.service";
import {OfferListModel} from "../../models/offerListModel";
import {getLoggedUser} from "../../../shared/getLogged";

@Component({
  selector: 'app-offer-form',
  templateUrl: './user-offer.component.html',
  styleUrls: ['./user-offer.component.css']
})
export class UserOfferComponent implements OnInit {

    offers:  OfferListModel[] = [];
    offersView:  OfferListModel[] = [];
    user: UserModel = getLoggedUser();
    totalRecords: number;
    perPage = 6;

    constructor(
        private itemService: ItemService,
        private offerService: OfferService,
        private route: ActivatedRoute,
        private router: Router
    ) { }

    ngOnInit(): void {
        this.getAllUserOffers();
    }

    getAllUserOffers() {
        this.offerService.getAllForUser(this.user.id).subscribe( offers => {
            this.offers = [...offers];
            this.totalRecords = this.offers.length;
            this.offersView = offers.splice(0, this.perPage);
        });
    }

    paginate(event){
        this.offersView = [...this.offers].splice(event.page * this.perPage, this.perPage);
    }

    selectOfer(offer: OfferListModel){
        const navigationExtras: NavigationExtras = {
            queryParams: {
                offerId: JSON.stringify(offer.id)
            }
        };
        this.router.navigate(['/admin/offer-view'], navigationExtras);
    }

}
