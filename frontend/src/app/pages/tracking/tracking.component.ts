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
import { SearchPipe } from '../../search.pipe';
import { ToastrService } from 'ngx-toastr';
import { HttpClientModule, HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-tracking',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterComponent,
    ReactiveFormsModule,
    FormsModule,
    SearchPipe,
    HttpClientModule,
  ],
  templateUrl: './tracking.component.html',
  styleUrl: './tracking.component.css',
})
export class TrackingComponent implements OnInit {
  searchText1: string = '';
  searchText2: string = '';
  user: any = {};
  savedFoods: any[] = [];
  saved: any[] = [];
  excersiceTrackings: any[] = [];
  foodTrackings: any[] = [];
  constructor(private toast: ToastrService, private api: HttpClient) {}

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('user') || '{}');
    this.getUserSavedExcersices();
    this.getUserSavedFoods();
    this.getExcersiceTrackings();
    this.getFoodTrackings();
  }

  getExcersiceTrackings() {
    this.api.get('http://localhost:8080/userExerciseTracking').subscribe({
      next: (res: any) => {
        this.excersiceTrackings = res;
      },
      error: (err) => {
        this.toast.error(err.error.msg, 'Error');
      },
    });
  }

  getFoodTrackings() {
    this.api.get('http://localhost:8080/userFoodTracking').subscribe({
      next: (res: any) => {
        this.foodTrackings = res;
        console.log(res, 'resfood');
      },
      error: (err) => {
        this.toast.error(err.error.msg, 'Error');
      },
    });
  }

  getUserSavedExcersices() {
    const user = JSON.parse(sessionStorage.getItem('user') || '{}');
    this.api
      .get('http://localhost:8080/user/savedExercise?userId=' + user._id)
      .subscribe({
        next: (res: any) => {
          this.saved = res;
          // console.log(res, 'resexcersice');
        },
        error: (err) => {
          this.toast.error(err.error.msg, 'Error');
        },
      });
  }

  getUserSavedFoods() {
    const user = JSON.parse(sessionStorage.getItem('user') || '{}');
    this.api
      .get('http://localhost:8080/user/savedFood?userId=' + user._id)
      .subscribe({
        next: (res: any) => {
          this.savedFoods = res;
          // console.log(res, 'foodres');
        },
        error: (err) => {
          this.toast.error(err.error.msg, 'Error');
        },
      });
  }
  addForm: FormGroup = new FormGroup({
    createdAt: new FormControl('', [Validators.required]),
    exercise_id: new FormControl('', [Validators.required]),
    exercise_period: new FormControl('', [Validators.required]),
  });
  foodForm: FormGroup = new FormGroup({
    createdAt: new FormControl('', [Validators.required]),
    foodRecommendation_id: new FormControl('', [Validators.required]),
    quantityInTake: new FormControl('', [Validators.required]),
  });
  submitted = false;
  submit() {
    this.submitted = true;
    let data: any = {
      user_id: this.user._id,
      exercise_id: this.addForm.value.exercise_id,
      exercise_period: this.addForm.value.exercise_period,
      createdAt: this.addForm.value.createdAt,
    };
    let calories = '';
    this.saved.forEach((element) => {
      if (element.excercise.exerciseName === data.exercise_id) {
        calories = element.excercise.noOfCalories;
      }
    });

    const caloriesBurnt = Number(calories) * Number(data.exercise_period);
    data.totalLooseCalories = caloriesBurnt;
    if (this.addForm.valid) {
      this.api
        .post('http://localhost:8080/userExerciseTracking', data)
        .subscribe({
          next: (res: any) => {
            this.addForm.reset();
            this.getExcersiceTrackings();
            this.toast.success('Exercise Added Successfully');
          },
          error: (err) => {
            this.toast.error(err.error.msg, 'Error');
          },
        });
    } else {
      this.toast.error('Please fill all the fields');
    }
  }

  submit1() {
    this.submitted = true;
    let data: any = {
      user_id: this.user._id,
      foodRecommendation_id: this.foodForm.value.foodRecommendation_id,
      quantityInTake: this.foodForm.value.quantityInTake,
      createdAt: this.foodForm.value.createdAt,
    };
    let calories = '';
    this.savedFoods.forEach((element) => {
      if (element.food.name === data.foodRecommendation_id) {
        calories = element.food.noOfCalories;
      }
    });
    const caloriesIntake = Number(calories) * Number(data.quantityInTake);
    data.totalGainedCalories = caloriesIntake;
    if (this.foodForm.valid) {
      this.api.post('http://localhost:8080/userFoodTracking', data).subscribe({
        next: (res: any) => {
          this.foodForm.reset();
          this.getFoodTrackings();
          this.toast.success('Food Added Successfully');
        },
        error: (err) => {
          this.toast.error(err.error.msg, 'Error');
        },
      });
    } else {
      this.toast.error('Please fill all the fields');
    }
  }
}
