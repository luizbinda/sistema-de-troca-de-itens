import {SafeResourceUrl} from "@angular/platform-browser";

export class ImageModel {
    constructor(
        public id?: number,
        public photo?: string,
        public description?: string,
        public id_item?: number
    ) {}
}
