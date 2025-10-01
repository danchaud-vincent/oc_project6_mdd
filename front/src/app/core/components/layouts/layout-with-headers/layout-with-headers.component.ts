import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../header/header.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { map, Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  imports: [
    HeaderComponent,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    AsyncPipe,
    RouterOutlet,
  ],
  templateUrl: './layout-with-headers.component.html',
  styleUrl: './layout-with-headers.component.scss',
})
export class LayoutWithHeadersComponent implements OnInit {
  isMobile$!: Observable<boolean>;
  fixedTopGap$!: Observable<number>;

  constructor(private breakPointObserver: BreakpointObserver) {}

  ngOnInit(): void {
    this.isMobile$ = this.breakPointObserver
      .observe([Breakpoints.XSmall])
      .pipe(map((result) => result.matches));

    this.fixedTopGap$ = this.isMobile$.pipe(
      map((isMobile) => (isMobile ? 56 : 64))
    );
  }
}
