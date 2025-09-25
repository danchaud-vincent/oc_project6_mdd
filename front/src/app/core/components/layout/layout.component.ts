import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { map, Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-layout',
  imports: [
    HeaderComponent,
    MatSidenavModule,
    MatListModule,
    MatIconModule,
    AsyncPipe,
  ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss',
})
export class LayoutComponent implements OnInit {
  isMobile$!: Observable<boolean>;
  fixedTopGap$!: Observable<number>;

  constructor(private breakPointObserver: BreakpointObserver) {}

  ngOnInit(): void {
    this.isMobile$ = this.breakPointObserver
      .observe([Breakpoints.XSmall, Breakpoints.Small])
      .pipe(map((result) => result.matches));

    this.fixedTopGap$ = this.isMobile$.pipe(
      map((isMobile) => (isMobile ? 56 : 64))
    );
  }
}
