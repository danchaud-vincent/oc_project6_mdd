import { Component, OnInit } from '@angular/core';
import { ButtonBackwardComponent } from '../../../../shared/components/button-backward/button-backward.component';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ButtonComponent } from '../../../../shared/components/button/button.component';

@Component({
  selector: 'app-add-post',
  imports: [
    ButtonBackwardComponent,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ButtonComponent,
  ],
  templateUrl: './add-post.component.html',
  styleUrl: './add-post.component.scss',
})
export class AddPostComponent implements OnInit {
  addPostForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.addPostForm = this.formBuilder.group({
      topic: [null],
      title: [null],
      content: [null],
    });
  }

  onSubmitForm() {
    console.log(this.addPostForm.value);
  }
}
