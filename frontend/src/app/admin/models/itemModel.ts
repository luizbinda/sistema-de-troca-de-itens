import {UserModel} from "./userModel";
import {CategoryModel} from "./categoryModel";
import {ImageModel} from "./ImageModel";

export class ItemModel {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public available?: boolean,
        public user?: UserModel,
        public category?: CategoryModel,
        public images?: ImageModel[]
    ) {
        this.category = new CategoryModel();
        this.user = new UserModel();
        this.images = [];
    }
}
