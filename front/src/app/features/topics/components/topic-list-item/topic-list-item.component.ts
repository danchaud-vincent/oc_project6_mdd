import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Topic } from '../../models/topic.model';
import { CardComponent } from '../../../../shared/components/card/card.component';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { TopicsService } from '../../services/topics.service';

@Component({
  selector: 'app-topic-list-item',
  imports: [CardComponent, ButtonComponent],
  templateUrl: './topic-list-item.component.html',
  styleUrl: './topic-list-item.component.scss',
})
export class TopicListItemComponent {
  @Input() topic!: Topic;
  @Input() isUserProfile: boolean = false;
  @Output() subscriptionEvent = new EventEmitter<Topic>();

  constructor(private topicsService: TopicsService) {}

  getSubscriptionLabel(): string {
    if (this.isUserProfile && this.topic.isSubscribed) {
      return 'Se désabonner';
    }

    return this.topic.isSubscribed ? 'Déjà abonné' : "S'abonner";
  }

  isDisabled(): boolean {
    if (this.isUserProfile) {
      return false;
    }

    if (this.topic.isSubscribed) {
      return true;
    }

    return false;
  }

  onClick(): void {
    this.subscriptionEvent.emit(this.topic);
  }
}
