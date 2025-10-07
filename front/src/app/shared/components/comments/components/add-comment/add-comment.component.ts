import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-add-comment',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatIcon],
  templateUrl: './add-comment.component.html',
  styleUrl: './add-comment.component.scss',
})
export class AddCommentComponent {}
