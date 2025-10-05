import {
  AfterContentInit,
  Component,
  ContentChildren,
  QueryList,
} from '@angular/core';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-card',
  imports: [MatCardModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss',
})
export class CardComponent implements AfterContentInit {
  hasActions = false;

  @ContentChildren('actions', { descendants: true }) actions!: QueryList<any>;

  ngAfterContentInit(): void {
    this.hasActions = this.actions.length > 0;
  }
}
