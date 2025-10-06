import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { passwordValidator } from '../../../auth/validators/password.validator';
import { ButtonComponent } from '../../../../shared/components/button/button.component';

@Component({
  selector: 'app-me',
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    ButtonComponent,
  ],
  templateUrl: './me.component.html',
  styleUrl: './me.component.scss',
})
export class MeComponent implements OnInit {
  meForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.meForm = this.formBuilder.group({
      username: [null, [Validators.required]],
      email: [null, [Validators.required]],
      password: [null, [Validators.required, passwordValidator()]],
    });
  }
}
