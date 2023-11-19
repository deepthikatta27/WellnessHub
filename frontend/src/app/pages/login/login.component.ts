import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormsModule,
  FormGroup,
  FormControl,
  Validators,
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  showLoginForm = true;
  submitted = false;
  selectedFile: any;
  currentFile: any;
  editForm: any;
  constructor(
    private toast: ToastrService,
    private router: Router,
    private api: HttpClient
  ) {}
  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });
  registerForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
    image: new FormControl('', [Validators.required]),
    age: new FormControl('', [Validators.required]),
    height: new FormControl('', [Validators.required]),
    weight: new FormControl('', [Validators.required]),
  });
  // String _id,String name, MultipartFile image, String email, String password, Integer age, Integer height, Integer weight
  onSubmit() {
    this.submitted = true;
    // console.log(this.loginForm.value);
    if (this.loginForm.valid) {
      this.api
        .post('http://localhost:8080/userLogin', this.loginForm.value)
        .subscribe({
          next: (res: any) => {
            this.submitted = false;
            sessionStorage.setItem('user', JSON.stringify(res));
            this.router.navigate(['/']);
          },
          error: (err: any) => {
            console.log(err, 'error');
            this.toast.error('Login Failed', 'Api error');
          },
        });
    } else {
      this.toast.error('Please enter valid details');
    }
  }

  uploadFile(event: any) {
    this.selectedFile = event.target.files[0];
    this.currentFile = this.selectedFile;
    if (
      this.currentFile?.type != 'image/jpeg' &&
      this.currentFile?.type != 'image/JPEG' &&
      this.currentFile?.type != 'image/png' &&
      this.currentFile?.type != 'image/jpg' &&
      this.currentFile?.type != 'image/JPG' &&
      this.currentFile?.type != 'image/PNG'
    ) {
      // this.isFile = false;
      this.selectedFile = '';
      this.currentFile = '';
      this.registerForm.get('image')?.setValue('');
      this.toast.error('Only jpeg, jpg and png format allowed');
    } else {
      // this.isFile = true;
      this.selectedFile = event.target.files[0];
    }
  }

  register() {
    this.submitted = true;
    // console.log(this.registerForm.value);
    let formData = new FormData();
    formData.append('name', this.registerForm.value.name as string);
    formData.append('email', this.registerForm.value.email as string);
    formData.append('password', this.registerForm.value.password as string);
    formData.append('age', this.registerForm.value.age as string);
    formData.append('height', this.registerForm.value.height as string);
    formData.append('weight', this.registerForm.value.weight as string);
    formData.append('image', this.selectedFile);
    if (this.registerForm.valid) {
      this.api.post('http://localhost:8080/user', formData).subscribe({
        next: (res: any) => {
          this.submitted = false;
          this.registerForm.reset();
          this.changeToLogin();
          this.toast.success('Registration Successfull');
        },
        error: (err: any) => {
          console.log(err, 'error');
          this.toast.error('Registration Failed');
        },
      });
    } else {
      this.toast.error('Please enter valid details');
    }
  }
  changeToLogin() {
    this.showLoginForm = true;
  }
  changeToRegister() {
    this.showLoginForm = false;
  }
}
