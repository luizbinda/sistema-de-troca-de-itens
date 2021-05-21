import {Component, Input, OnInit} from '@angular/core';
import {ItemModel} from "../../models/itemModel";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {ConfirmationService} from "primeng";
import {ActivatedRoute, NavigationExtras, Router} from "@angular/router";

@Component({
  selector: 'app-item-card',
  templateUrl: './item-card.component.html',
  styleUrls: ['./item-card.component.scss'],
  providers: [ConfirmationService]
})
export class ItemCardComponent implements OnInit {

  @Input() item: ItemModel;

  constructor(
      private sanitizer: DomSanitizer,
      private router: Router
      ) {}

  ngOnInit(): void {
  }

    urlImage(photo: string): SafeResourceUrl {
      return this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/png;base64, ${photo}`);
    }

    redirectOffer() {
        const navigationExtras: NavigationExtras = {
            queryParams: {
                itemId: JSON.stringify(this.item.id)
            }
        };
        this.router.navigate(['/admin/offer'], navigationExtras);
    }
}
