import { Component, OnInit } from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {ItemService} from "../../../services/item.service";
import {UserModel} from "../../models/userModel";
import {ActivatedRoute, Router} from "@angular/router";
import {OfferService} from "../../../services/offer.service";

@Component({
  selector: 'app-offer-form',
  templateUrl: './offer-form.component.html',
  styleUrls: ['./offer-form.component.css']
})
export class OfferFormComponent implements OnInit {

    offers: ItemModel[] = [];
    offersView: ItemModel[] = [];
    user: UserModel = new UserModel(1) ;
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

    selectOfer(){

    }

}
