import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    private api = 'api/users';

  constructor(private http: HttpClient) { }
    index() {
        return this.http.get<any[]>(this.api);
    }

    store(user) {
        return this.http.post(this.api, user);
    }

    update(user) {
        return this.http.put(this.api, user);
    }

    show(id: number) {
        return this.http.get(`${this.api}/${id}`);
    }

    delete(id: number) {
        return this.http.delete(`${this.api}/${id}`);
    }
}
