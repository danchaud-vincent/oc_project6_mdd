import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-button-backward',
  imports: [MatIconModule],
  templateUrl: './button-backward.component.html',
  styleUrl: './button-backward.component.scss',
})
export class ButtonBackwardComponent {
  back() {
    window.history.back();
  }
}
