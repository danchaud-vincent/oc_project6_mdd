import { Injectable } from '@angular/core';
import { UserSessionInfo } from '../models/userSessionInfo.model';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserSessionService {
  private userSessionInfo: UserSessionInfo | undefined;
  private isLogged = false;
  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  public isLogged$(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public login(userInfo: UserSessionInfo): void {
    this.userSessionInfo = userInfo;
    this.isLogged = true;
    this.isLoggedSubject.next(this.isLogged);
  }

  public logout(): void {
    this.userSessionInfo = undefined;
    this.isLogged = false;
    this.isLoggedSubject.next(this.isLogged);
  }

  public getToken(): string {
    return this.userSessionInfo ? this.userSessionInfo.token : '';
  }
}
