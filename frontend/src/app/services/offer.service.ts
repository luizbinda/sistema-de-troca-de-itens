import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {OfferModel} from "../admin/models/offerModel";

@Injectable({
  providedIn: 'root'
})
export class OfferService {

    private api = 'api/offers';

  constructor(private http: HttpClient) { }
    index() {
        return this.http.get<OfferModel[]>(this.api);
    }

    store(offer) {
        return this.http.post(this.api, offer);
    }

    update(offer) {
        return this.http.put(this.api, offer);
    }

    show(id: number) {
        return this.http.get<OfferModel>(`${this.api}/${id}`);
    }

}
