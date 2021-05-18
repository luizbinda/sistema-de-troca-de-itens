import { Component, OnInit } from '@angular/core';
import { PageNotificationService } from '@nuvem/primeng-components';
import {ItemService} from "../../../services/item.service";
import {ItemModel} from "../../models/itemModel";

@Component({
  selector: 'app-item-index',
  templateUrl: './item-index.component.html',
  styleUrls: ['./item-index.component.css']
})
export class ItemIndexComponent implements OnInit {

    items: ItemModel[] = [];
    displayModal = false;

    constructor(
      private itemService: ItemService,
      private notification: PageNotificationService
    ) {}

    ngOnInit(): void {
      this.getAllItems();
    }

    getAllItems() {
      this.itemService.index().subscribe( items => this.items = items);
    }

    showModalDialog(value: boolean) {
        this.displayModal = value;
    }

    closeModalDialog() {
        this.showModalDialog(false);
    }
}
