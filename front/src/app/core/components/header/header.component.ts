import { Component, Input } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenav } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { UserSessionService } from '../../services/user-session.service';

@Component({
  selector: 'app-header',
  imports: [
    RouterModule,
    RouterLink,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    AsyncPipe,
    RouterLinkActive,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  @Input() matSidenav!: MatSidenav;
  @Input() isMobile$!: Observable<boolean>;

  constructor(private userSessionService: UserSessionService) {}

  logout() {
    this.userSessionService.logout();
  }
}
