import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Me } from '../models/me.model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class MeService {
  constructor(private http: HttpClient) {}

  getMe(): Observable<Me> {
    return this.http.get<Me>(`${environment.baseUrl}/users/me`);
  }
}
