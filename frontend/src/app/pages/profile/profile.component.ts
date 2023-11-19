import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import {
  ReactiveFormsModule,
  FormsModule,
  FormGroup,
  FormControl,
  Validators,
} from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { HttpClientModule, HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterComponent,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent implements OnInit {
  selectedFile: any;
  currentFile: any;
  userData: any;
  constructor(private toast: ToastrService, private api: HttpClient) {}
  ngOnInit(): void {
    this.userData = JSON.parse(sessionStorage.getItem('user') || '{}');
    // console.log(this.userData, 'user data');
    this.editForm.patchValue({
      name: this.userData.name,
      email: this.userData.email,
      height: this.userData.height,
      weight: this.userData.weight,
      age: this.userData.age,
      // image: this.userData.image,
    });
  }

  // uploadFile(event: any) {
  //   this.selectedFile = event.target.files[0];
  //   this.currentFile = this.selectedFile;
  //   if (
  //     this.currentFile?.type != 'image/jpeg' &&
  //     this.currentFile?.type != 'image/JPEG' &&
  //     this.currentFile?.type != 'image/png' &&
  //     this.currentFile?.type != 'image/jpg' &&
  //     this.currentFile?.type != 'image/JPG' &&
  //     this.currentFile?.type != 'image/PNG'
  //   ) {
  //     // this.isFile = false;
  //     this.selectedFile = '';
  //     this.currentFile = '';
  //     this.editForm.get('image')?.setValue('');
  //     this.toast.error('Only jpeg, jpg and png format allowed');
  //   } else {
  //     // this.isFile = true;
  //     this.selectedFile = event.target.files[0];
  //   }
  // }
  editForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    height: new FormControl('', [Validators.required]),
    weight: new FormControl('', [Validators.required]),
    age: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });
  submitted = false;
  submit() {
    this.submitted = true;
    let data: any = {
      name: this.editForm.value.name,
      email: this.editForm.value.email,
      height: this.editForm.value.height,
      weight: this.editForm.value.weight,
      age: this.editForm.value.age,
      password: this.editForm.value.password,
      // image:this.editForm.value.image,
      _id: this.userData._id,
    };

    if (this.editForm.valid) {
      this.api.put('http://localhost:8080/user', data).subscribe({
        next: (res: any) => {
          sessionStorage.clear();
          window.location.href = '/login';
          this.toast.success('Profile updated successfully');
        },
        error: (err: any) => {
          // console.log(err,"err");
          this.toast.error('Something went wrong', 'Api error');
        },
      });
    } else {
      this.toast.error('Please fill all the fields');
    }
  }
}
