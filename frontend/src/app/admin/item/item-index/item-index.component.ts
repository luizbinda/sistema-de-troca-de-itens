import { Component, OnInit } from '@angular/core';
import { PageNotificationService } from '@nuvem/primeng-components';
import {ItemService} from "../../../services/item.service";
import {ItemModel} from "../../models/itemModel";
import {UserModel} from "../../models/userModel";

@Component({
  selector: 'app-item-index',
  templateUrl: './item-index.component.html',
  styleUrls: ['./item-index.component.css']
})
export class ItemIndexComponent implements OnInit {

    items: ItemModel[] = [];
    displayModal = false;
    itemEdit: ItemModel = new ItemModel();
    user : UserModel = new UserModel(1)
    constructor(
      private itemService: ItemService,
    ) {}

    ngOnInit(): void {
      this.getAllItems();
    }

    getAllItems() {
      this.itemService.findAllByUserId(this.user.id).subscribe( items => this.items = items);
    }

    showModalDialog(value: boolean) {
        this.displayModal = value;
    }

    showModalDialogEdite(item: ItemModel) {
        this.displayModal = true;
        this.itemEdit = item;
    }

    closeModalDialog() {
        this.showModalDialog(false);
    }
}