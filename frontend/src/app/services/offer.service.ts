import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {OfferModel} from "../admin/models/offerModel";
import {OfferListModel} from "../admin/models/offerListModel";

@Injectable({
  providedIn: 'root'
})
export class OfferService {

    private api = 'api/offers';

  constructor(private http: HttpClient) { }

    index(itemId) {
        return this.http.get<OfferListModel[]>(`${this.api}/item/${itemId}`);
    }

    getAllForUser(userId) {
        return this.http.get<OfferListModel[]>(`${this.api}/user/${userId}`);
    }

    store(offer: OfferModel) {
        return this.http.post(this.api, offer);
    }

    accept(idOffer: number) {
        return this.http.patch(`${this.api}/accepted/${idOffer}`, {});
    }

    refuse(idOffer: number) {
        return this.http.patch(`${this.api}/refused/${idOffer}`, {});
    }

    update(offer: OfferModel) {
        return this.http.put(this.api, offer);
    }

    show(id: number) {
        return this.http.get<OfferListModel>(`${this.api}/${id}`);
    }

}
