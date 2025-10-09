import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { Comment } from '../../models/comment.model';
import { MatGridListModule } from '@angular/material/grid-list';
import { DatePipe } from '@angular/common';
import { AddCommentComponent } from '../add-comment/add-comment.component';

@Component({
  selector: 'app-comments-list',
  imports: [MatListModule, MatGridListModule, DatePipe, AddCommentComponent],
  templateUrl: './comments-list.component.html',
  styleUrl: './comments-list.component.scss',
})
export class CommentsListComponent {
  @Input() comments!: Comment[];
  @Output() newCommentAdded = new EventEmitter<string>();

  onNewComment(commentValue: string) {
    this.newCommentAdded.emit(commentValue);
  }
}
