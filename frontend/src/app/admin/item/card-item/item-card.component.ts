import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {ViewEncapsulation} from "@angular/cli/lib/config/schema";

@Component({
  selector: 'app-item-card',
  templateUrl: './item-card.component.html',
  styleUrls: ['./item-card.component.scss']
})
export class ItemCardComponent implements OnInit {

  @Input() item: ItemModel;
  @Output() editItem = new EventEmitter<ItemModel>();

  responsiveOptions;

    constructor() {
      this.responsiveOptions = [
          {
              breakpoint: '1024px',
              numVisible: 3,
              numScroll: 3
          },
          {
              breakpoint: '768px',
              numVisible: 2,
              numScroll: 2
          },
          {
              breakpoint: '560px',
              numVisible: 1,
              numScroll: 1
          }
      ];
    }

  ngOnInit(): void {
  }

  emitEditItem() {
      this.editItem.emit(this.item);
  }



}
