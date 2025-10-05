import { Component, OnInit } from '@angular/core';
import { ButtonBackwardComponent } from '../../../../shared/components/button-backward/button-backward.component';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ButtonComponent } from '../../../../shared/components/button/button.component';
import { Topic } from '../../../topics/models/topic.model';
import { TopicsService } from '../../../topics/services/topics.service';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-add-post',
  imports: [
    ButtonBackwardComponent,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ButtonComponent,
    AsyncPipe,
  ],
  templateUrl: './add-post.component.html',
  styleUrl: './add-post.component.scss',
})
export class AddPostComponent implements OnInit {
  addPostForm!: FormGroup;
  topics$!: Observable<Topic[]>;

  constructor(
    private formBuilder: FormBuilder,
    private topicsService: TopicsService
  ) {}

  ngOnInit(): void {
    this.addPostForm = this.formBuilder.group({
      topic: [null, [Validators.required]],
      title: [null, [Validators.required]],
      content: [null, Validators.required],
    });

    this.topics$ = this.topicsService.getTopics();
  }

  onSubmitForm() {
    console.log(this.addPostForm.value);
  }
}
