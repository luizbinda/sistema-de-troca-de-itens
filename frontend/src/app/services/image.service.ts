import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ImageModel} from "../admin/models/ImageModel";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

    private api = 'api/images';

  constructor(private http: HttpClient) { }
    index() {
        return this.http.get<ImageModel[]>(this.api);
    }

    store(data: FormData) {
        return this.http.post<ImageModel>(this.api, data);
    }

    update(data: FormData) {
        return this.http.put<ImageModel>(this.api, data);
    }

    show(id: number) {
        return this.http.get<ImageModel>(`${this.api}/${id}`);
    }

    delete(id: number) {
        return this.http.delete(`${this.api}/${id}`);
    }
}
