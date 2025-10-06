import { Component, Input } from '@angular/core';
import { Topic } from '../../models/topic.model';
import { CardComponent } from '../../../../shared/components/card/card.component';
import { ButtonComponent } from '../../../../shared/components/button/button.component';

@Component({
  selector: 'app-topic-list-item',
  imports: [CardComponent, ButtonComponent],
  templateUrl: './topic-list-item.component.html',
  styleUrl: './topic-list-item.component.scss',
})
export class TopicListItemComponent {
  @Input() topic!: Topic;
}
