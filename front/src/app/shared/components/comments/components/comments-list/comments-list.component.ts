import { Component, Input } from '@angular/core';
import { MatListModule } from '@angular/material/list';
import { Comment } from '../../models/comment.model';
import { MatGridListModule } from '@angular/material/grid-list';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-comments-list',
  imports: [MatListModule, MatGridListModule, DatePipe],
  templateUrl: './comments-list.component.html',
  styleUrl: './comments-list.component.scss',
})
export class CommentsListComponent {
  @Input() comments!: Comment[];
}
