import {UserModel} from "./userModel";
import {CategoryModel} from "./categoryModel";

export class ItemModel {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public available?: boolean,
        public user?: UserModel,
        public category?: CategoryModel
    ) {
        this.category = new CategoryModel();
        this.user = new UserModel();
    }
}
