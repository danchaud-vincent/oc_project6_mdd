import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { Me } from '../models/me.model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { UserSessionInfo } from '../../../core/models/userSessionInfo.model';
import { MeUpdateRequest } from '../models/meUpdateRequest.model';
import { UserSessionService } from '../../../core/services/user-session.service';

@Injectable({
  providedIn: 'root',
})
export class MeService {
  constructor(
    private http: HttpClient,
    private userSessionService: UserSessionService
  ) {}

  getMe(): Observable<Me> {
    return this.http.get<Me>(`${environment.baseUrl}/users/me`);
  }

  updateMeInfo(meUpdateRequest: MeUpdateRequest): Observable<Me> {
    return this.http
      .put<UserSessionInfo>(`${environment.baseUrl}/users/me`, meUpdateRequest)
      .pipe(
        tap((newUserSessionInfo) => {
          this.userSessionService.updateUserSessionInfo(newUserSessionInfo);
        }),
        map((newUserSessionInfo) => {
          return {
            id: newUserSessionInfo.id,
            username: newUserSessionInfo.username,
            email: newUserSessionInfo.email,
          };
        })
      );
  }
}
