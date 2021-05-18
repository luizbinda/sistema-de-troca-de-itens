import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ItemModel} from "../../models/itemModel";

@Component({
  selector: 'app-item-card',
  templateUrl: './item-card.component.html',
  styleUrls: ['./item-card.component.css']
})
export class ItemCardComponent implements OnInit {

  @Input() item: ItemModel;
  @Output() editItem = new EventEmitter<ItemModel>();

  ngOnInit(): void {
  }

  emitEditItem() {
      this.editItem.emit(this.item);
  }



}
