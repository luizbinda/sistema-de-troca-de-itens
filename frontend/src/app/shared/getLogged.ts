import {UserModel} from "../admin/models/userModel";

export const getLoggedUser = (): UserModel => {
    return JSON.parse(localStorage.getItem('user'));
}
