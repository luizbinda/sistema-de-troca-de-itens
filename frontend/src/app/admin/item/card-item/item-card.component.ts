import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Component({
  selector: 'app-item-card',
  templateUrl: './item-card.component.html',
  styleUrls: ['./item-card.component.scss']
})
export class ItemCardComponent implements OnInit {

  @Input() item: ItemModel;
  @Output() editItem = new EventEmitter<ItemModel>();

  constructor(private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
  }

    urlImage(photo: string): SafeResourceUrl {
      return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${photo}`);
    }

    emitEditItem() {
      this.editItem.emit(this.item);
  }



}
