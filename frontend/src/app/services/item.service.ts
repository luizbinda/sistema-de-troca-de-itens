import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ItemModel} from "../admin/models/itemModel";

@Injectable({
  providedIn: 'root'
})
export class ItemService {

    private api = 'api/itens';

  constructor(private http: HttpClient) { }
    index() {
        return this.http.get<ItemModel[]>(this.api);
    }

    findAllByUserId(userId) {
        return this.http.get<ItemModel[]>(`${this.api}/user/${userId}`);
    }

    store(item: ItemModel) {
        return this.http.post<ItemModel>(this.api, item);
    }

    update(item: ItemModel) {
        return this.http.put<ItemModel>(this.api, item);
    }

    show(id: number) {
        return this.http.get<ItemModel>(`${this.api}/${id}`);
    }

    delete(id: number) {
        return this.http.delete(`${this.api}/${id}`);
    }
}
