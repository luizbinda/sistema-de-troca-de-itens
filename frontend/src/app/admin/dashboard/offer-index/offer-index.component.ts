import { Component, OnInit } from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {ItemService} from "../../../services/item.service";

@Component({
  selector: 'app-offer-index',
  templateUrl: './offer-index.component.html',
  styleUrls: ['./offer-index.component.css']
})
export class OfferIndexComponent implements OnInit {

    items: ItemModel[] = [];
    itemsView: ItemModel[] = [];
    totalRecords: number;
    perPage = 6;

    constructor(
        private itemService: ItemService,
    ) {}

    ngOnInit(): void {
        this.getAllItems();
    }

    getAllItems() {
        this.itemService.index().subscribe( items => {
            this.items = [...items];
            this.totalRecords = this.items.length;
            this.itemsView = items.splice(0, this.perPage);
        });
    }

    paginate(event){
        this.itemsView = [...this.items].splice(event.page * this.perPage, this.perPage);
    }



}
