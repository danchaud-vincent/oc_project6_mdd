import {
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserSessionService } from '../services/user-session.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private userSessionService: UserSessionService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const headers = new HttpHeaders().append(
      'Authorization',
      `Bearer ${this.userSessionService.getToken()}`
    );

    const modifiedReq = req.clone({ headers: headers });

    return next.handle(modifiedReq);
  }
}
