import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import {
  AbstractControl,
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
      comment: [
        null,
        [
          Validators.required,
          Validators.minLength(5),
          Validators.pattern(/.*\S.*/),
        ],
      ],
    });
  }

  onSubmitForm(): void {
    if (this.addComment.invalid) {
      this.addComment.markAllAsTouched();
      return;
    }

    const commentValue: string = this.addComment.value.comment.trim();
    this.newComment.emit(commentValue);
    this.addComment.reset();
  }

  getFormControlErrorText(ctrl: AbstractControl): string {
    if (ctrl.hasError('required')) {
      return 'Ce champs est requis';
    } else if (ctrl.hasError('minlength')) {
      return 'Le commentaire doit posséder au minimum 5 caractères';
    } else {
      return 'Ce champs contient une erreur';
    }
  }
}
