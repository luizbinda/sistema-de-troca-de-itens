import {ItemModel} from "./itemModel";
import {UserModel} from "./userModel";

export class OfferListModel {
    constructor(
        public id?: number,
        public item?: ItemModel,
        public user?: UserModel,
        public itemsOffered?: ItemModel[]
    ) {
        this.itemsOffered = []
    }
}
