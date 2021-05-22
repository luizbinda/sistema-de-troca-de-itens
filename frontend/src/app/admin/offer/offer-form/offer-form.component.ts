import { Component, OnInit } from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {ItemService} from "../../../services/item.service";
import {UserModel} from "../../models/userModel";
import {ActivatedRoute, Router} from "@angular/router";
import {PageNotificationService} from "@nuvem/primeng-components";
import {Constants} from "../../../shared/Constants";
import {OfferService} from "../../../services/offer.service";
import {OfferModel} from "../../models/offerModel";

@Component({
  selector: 'app-offer-form',
  templateUrl: './offer-form.component.html',
  styleUrls: ['./offer-form.component.css']
})
export class OfferFormComponent implements OnInit {

    itemId: number;
    itemOffer: ItemModel;
    items: ItemModel[] = [];
    itemsView: ItemModel[] = [];
    itemsOffered: number[] = [];
    user: UserModel = new UserModel(1) ;
    totalRecords: number;
    perPage = 6;

    constructor(
        private itemService: ItemService,
        private offerService: OfferService,
        private route: ActivatedRoute,
        private router: Router,
        private notification: PageNotificationService

    ) {
        this.route.queryParams.subscribe(params => {
            if (params && params.itemId) {
                this.itemId = JSON.parse(params.itemId);
                this.findItemOffer(this.itemId);
            } else {
                this.notification.addErrorMessage(Constants.REDIRECT_ERROR);
                this.router.navigate(['/admin/dashboard']);
            }
       });
    }

    ngOnInit(): void {
        this.getAllUserItems();
    }

    findItemOffer(id: number) {
        this.itemService.show(id).subscribe(
            item => this.itemOffer = item,
            error => {
                this.notification.addErrorMessage(error.error.message);
            }
        )
    }

    getAllUserItems() {
        this.itemService.findAllByUserId(this.user.id).subscribe( items => {
            this.items = [...items];
            this.totalRecords = this.items.length;
            this.itemsView = items.splice(0, this.perPage);
        });
    }

    paginate(event){
        this.itemsView = [...this.items].splice(event.page * this.perPage, this.perPage);
    }

    isSelected(itemId : number) {
        if (this.itemsOffered.some( id => id === itemId)) {
            return 'selected'
        }
    }

    selectItem(itemId: number) {
        const index = this.itemsOffered.findIndex( id => id === itemId);
        if (-1 !== index) {
            this.itemsOffered.splice(index, 1);
        } else {
            this.itemsOffered.push(itemId);
        }
    }

    submit() {
        if (!this.itemsOffered.length) {
            this.notification.addErrorMessage(Constants.OFFER_SELECT_ITEMS_ERROR);
        }
        const offer = new OfferModel();
        offer.itemId = this.itemId;
        offer.userId = this.itemOffer.user.id;
        offer.itemsOffered = this.itemsOffered;
        this.offerService.store(offer).subscribe(
            () => {
                this.notification.addSuccessMessage(Constants.SAVED_SUCCESSFULY);
            },() => {
                this.notification.addErrorMessage(Constants.SAVED_ERROR);
            }
        );
    }



}
