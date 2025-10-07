import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-comments-list',
  imports: [],
  templateUrl: './comments-list.component.html',
  styleUrl: './comments-list.component.scss',
})
export class CommentsListComponent {
  @Input() comments!: string;
}
