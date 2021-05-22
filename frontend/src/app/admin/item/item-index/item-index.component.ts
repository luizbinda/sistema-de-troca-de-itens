import { Component, OnInit } from '@angular/core';
import {ItemService} from "../../../services/item.service";
import {ItemModel} from "../../models/itemModel";
import {UserModel} from "../../models/userModel";
import {getLoggedUser} from "../../../shared/getLogged";

@Component({
  selector: 'app-item-index',
  templateUrl: './item-index.component.html',
  styleUrls: ['./item-index.component.css']
})
export class ItemIndexComponent implements OnInit {

    items: ItemModel[] = [];
    displayModal = false;
    itemEdit: ItemModel = new ItemModel();
    user : UserModel = getLoggedUser();
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
        this.itemEdit = item;
        this.displayModal = true;
    }

    closeModalDialog() {
        this.showModalDialog(false);
    }
}
