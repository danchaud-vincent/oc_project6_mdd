import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
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

  constructor(private topicsService: TopicsService) {}

  ngOnInit(): void {
    this.topics$ = this.topicsService.getTopics();
  }
}
