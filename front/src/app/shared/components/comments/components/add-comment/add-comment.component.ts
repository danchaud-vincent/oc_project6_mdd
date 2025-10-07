import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-add-comment',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatIcon],
  templateUrl: './add-comment.component.html',
  styleUrl: './add-comment.component.scss',
})
export class AddCommentComponent implements OnInit {
  addComment!: FormGroup;
  @Output() newComment = new EventEmitter<string>();

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.addComment = this.formBuilder.group({
      comment: [null, [Validators.required]],
    });
  }

  onSubmitForm(): void {
    console.log(this.addComment.value.comment);
  }
}
