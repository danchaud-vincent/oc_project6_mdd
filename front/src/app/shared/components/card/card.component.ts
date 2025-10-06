import {
  AfterContentInit,
  Component,
  ContentChildren,
  HostBinding,
  Input,
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
  hasActions = true;
  @ContentChildren(ButtonComponent) items!: QueryList<ButtonComponent>;
  @Input() clickable = false;

  ngAfterContentInit(): void {
    if (this.items.length > 0) {
      this.hasActions = true;
    }
  }
}
