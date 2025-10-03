import { Component } from '@angular/core';
import { ButtonBackwardComponent } from '../../../../shared/components/button-backward/button-backward.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-add-post',
  imports: [
    ButtonBackwardComponent,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
  ],
  templateUrl: './add-post.component.html',
  styleUrl: './add-post.component.scss',
})
export class AddPostComponent {
  onCreatePost() {
    throw new Error('Method not implemented.');
  }
}
