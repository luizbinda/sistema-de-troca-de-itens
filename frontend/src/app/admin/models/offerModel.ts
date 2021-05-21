export class OfferModel {
    constructor(
        public id?: number,
        public itemId?: number,
        public userId?: number,
        public itemsOffered?: number[]
    ) {
        this.itemsOffered = []
    }
}
