import { Component, OnInit } from '@angular/core';
import { forkJoin, map, Observable, switchMap, tap } from 'rxjs';
import { Topic } from '../../models/topic.model';
import { TopicsService } from '../../services/topics.service';
import { AsyncPipe } from '@angular/common';
import { TopicListItemComponent } from '../topic-list-item/topic-list-item.component';

@Component({
  selector: 'app-topic-list',
  imports: [AsyncPipe, TopicListItemComponent],
  templateUrl: './topic-list.component.html',
  styleUrl: './topic-list.component.scss',
})
export class TopicListComponent implements OnInit {
  topics$!: Observable<Topic[]>;
  topicsSubscribed$!: Observable<Topic[]>;

  constructor(private topicsService: TopicsService) {}

  ngOnInit(): void {
    this.topics$ = this.loadTopics();
  }

  loadTopics(): Observable<Topic[]> {
    return forkJoin({
      topics: this.topicsService.getTopics(),
      subscriptions: (this.topicsSubscribed$ =
        this.topicsService.getTopicsSubscriptions()),
    }).pipe(
      map(({ topics, subscriptions }) => {
        return topics.map((topic) => {
          return {
            ...topic,
            isSubscribed: subscriptions.some(
              (subscription) => subscription.id === topic.id
            ),
          };
        });
      })
    );
  }

  onSubscription(topic: Topic) {
    if (topic.isSubscribed) {
      this.topics$ = this.topicsService
        .unsubscribe(topic.id)
        .pipe(switchMap(() => this.loadTopics()));
    } else {
      this.topics$ = this.topicsService
        .subscribe(topic.id)
        .pipe(switchMap(() => this.loadTopics()));
    }
  }
}
