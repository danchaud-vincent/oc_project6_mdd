import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { Me } from '../models/me.model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { UserSessionInfo } from '../../../core/models/userSessionInfo.model';
import { MeUpdateRequest } from '../models/meUpdateRequest.model';

@Injectable({
  providedIn: 'root',
})
export class MeService {
  constructor(private http: HttpClient) {}

  getMe(): Observable<Me> {
    return this.http.get<Me>(`${environment.baseUrl}/users/me`);
  }

  updateMeInfo(meUpdateRequest: MeUpdateRequest): Observable<UserSessionInfo> {
    return this.http.put<UserSessionInfo>(
      `${environment.baseUrl}/users/me`,
      meUpdateRequest
    );
  }
}
