import { Injectable } from '@angular/core';
import { UserSessionInfo } from '../models/userSessionInfo.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class UserSessionService {
  private isLoggedSubject = new BehaviorSubject<boolean>(false);
  private userSessionSubject = new BehaviorSubject<UserSessionInfo | null>(
    null
  );

  constructor(private tokenService: TokenService) {
    const token = tokenService.getToken();

    if (token) {
      this.isLoggedSubject.next(true);
    }
  }

  public isLogged$(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public getUserSessionInfo$(): Observable<UserSessionInfo | null> {
    return this.userSessionSubject.asObservable();
  }

  public login(userInfo: UserSessionInfo): void {
    this.tokenService.setToken(userInfo.token);
    this.isLoggedSubject.next(true);
    this.userSessionSubject.next(userInfo);
  }

  public logout(): void {
    this.tokenService.removeToken();
    this.isLoggedSubject.next(false);
    this.userSessionSubject.next(null);
  }

  public updateUserSessionInfo(newUserSessionInfo: UserSessionInfo): void {
    this.tokenService.removeToken();
    this.tokenService.setToken(newUserSessionInfo.token);
    this.isLoggedSubject.next(true);
    this.userSessionSubject.next(newUserSessionInfo);
  }
}
