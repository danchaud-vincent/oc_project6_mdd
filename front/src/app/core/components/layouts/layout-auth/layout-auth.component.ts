import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout-auth',
  imports: [MatButtonModule, MatIconModule, RouterOutlet],
  templateUrl: './layout-auth.component.html',
  styleUrl: './layout-auth.component.scss',
})
export class LayoutAuthComponent {
  back() {
    window.history.back();
  }
}
