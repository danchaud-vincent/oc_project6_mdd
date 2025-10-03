import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ButtonBackwardComponent } from '../../../../shared/components/button-backward/button-backward.component';

@Component({
  selector: 'app-layout-auth',
  imports: [RouterOutlet, ButtonBackwardComponent],
  templateUrl: './layout-auth.component.html',
  styleUrl: './layout-auth.component.scss',
})
export class LayoutAuthComponent {}
