import {
  AfterContentInit,
  Component,
  ContentChildren,
  QueryList,
} from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { ButtonComponent } from '../button/button.component';

@Component({
  selector: 'app-card',
  imports: [MatCardModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss',
})
export class CardComponent implements AfterContentInit {
  hasActions = false;
  @ContentChildren(ButtonComponent) items!: QueryList<ButtonComponent>;

  ngAfterContentInit(): void {
    if (this.items.length > 0) {
      this.hasActions = true;
    }
  }
}
