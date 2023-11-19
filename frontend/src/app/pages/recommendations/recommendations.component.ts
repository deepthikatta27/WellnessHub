import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { ToastrService } from 'ngx-toastr';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {
  ReactiveFormsModule,
  FormsModule,
  FormGroup,
  FormControl,
  Validators,
} from '@angular/forms';
@Component({
  selector: 'app-recommendations',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterComponent,
    SlickCarouselModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './recommendations.component.html',
  styleUrl: './recommendations.component.css',
})
export class RecommendationsComponent implements OnInit {
  excersices: any[] = [];
  foods: any[] = [];
  saved: any[] = [];
  savedFoods: any[] = [];
  filterForm: FormGroup = new FormGroup({
    height: new FormControl('', [Validators.required]),
    weight: new FormControl('', [Validators.required]),
  });
  constructor(private toast: ToastrService, private api: HttpClient) {}
  ngOnInit(): void {
    this.getExcersice();
    this.getFoods();
    this.getUserSavedExcersices();
    this.getUserSavedFoods();
  }

  filter() {
    const height = this.filterForm.get('height')?.value;
    const weight = this.filterForm.get('weight')?.value;
    this.api
      .get(`http://localhost:8080/exerciseRecommendation/${height}/${weight}`)
      .subscribe({
        next: (res: any) => {
          this.excersices = res;
        },
        error: (err) => {
          this.toast.error(err.error.msg, 'Error');
        },
      });
  }

  getExcersice() {
    this.api.get('http://localhost:8080/exerciseRecommendation').subscribe({
      next: (res: any) => {
        this.excersices = res;
        // console.log(res, 'res');
      },
      error: (err) => {
        this.toast.error(err.error.msg, 'Error');
      },
    });
  }

  getFoods() {
    this.api.get('http://localhost:8080/foodRecommendation').subscribe({
      next: (res: any) => {
        this.foods = res;
        console.log(res, 'res');
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
          console.log(res, 'resexcersice');
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

  saveExcersiceRcomendation(excersiseId: string) {
    const user = JSON.parse(sessionStorage.getItem('user') || '{}');
    const data = {
      userId: user._id,
      exerciseRecommendationId: excersiseId,
    };
    this.api
      .post('http://localhost:8080/savedExerciseRecommendation', data)
      .subscribe({
        next: (res: any) => {
          this.getUserSavedExcersices();
          // window.location.reload();
          this.toast.success('Excersice saved successfully', 'Success');
        },
        error: (err: any) => {
          this.toast.error(err.error.msg, 'Error');
        },
      });
  }

  saveFoodRcomendation(foodId: string) {
    const user = JSON.parse(sessionStorage.getItem('user') || '{}');
    const data = {
      userId: user._id,
      foodRecommendationId: foodId,
    };
    this.api
      .post('http://localhost:8080/savedFoodRecommendation', data)
      .subscribe({
        next: (res: any) => {
          this.getUserSavedFoods();
          // window.location.reload();
          this.toast.success('Food saved successfully', 'Success');
        },
        error: (err: any) => {
          this.toast.error(err.error.msg, 'Error');
        },
      });
    // this.toast.success('Food saved successfully', 'Success');
  }

  removeExcersiceRecommendation(id: string) {
    this.api
      .delete(`http://localhost:8080/savedExerciseRecommendation/${id}`)
      .subscribe({
        next: (res: any) => {
          this.getUserSavedExcersices();
          this.toast.success('Excersice removed successfully', 'Success');
        },
        error: (err: any) => {
          this.toast.error(err.error.msg, 'Error');
        },
      });
  }

  removeFoodRecommendation(id: string) {
    this.api
      .delete(`http://localhost:8080/savedFoodRecommendation/${id}`)
      .subscribe({
        next: (res: any) => {
          this.getUserSavedFoods();
          this.toast.success('Excersice removed successfully', 'Success');
        },
        error: (err: any) => {
          this.toast.error(err.error.msg, 'Error');
        },
      });
  }

  slideConfig = {
    slidesToShow: 4,
    slidesToScroll: 4,
    // fade: true,
    // autoplay: true,
    // autoplaySpeed: 2000,
    // speed: 1000,
    // dots: true,
    // arrows: true,
  };

  slideConfig_two = {
    slidesToShow: 4,
    slidesToScroll: 4,
    // fade: true,
    // autoplay: true,
    // autoplaySpeed: 2000,
    // speed: 1000,
    // dots: true,
    // arrows: true,
  };

  slideConfig_three = {
    slidesToShow: 4,
    slidesToScroll: 4,
    // fade: true,
    // autoplay: true,
    // autoplaySpeed: 2000,
    // speed: 1000,
    // dots: true,
    // arrows: true,
  };

  slideConfig_four = {
    slidesToShow: 4,
    slidesToScroll: 4,
    // fade: true,
    // autoplay: true,
    // autoplaySpeed: 2000,
    // speed: 1000,
    // dots: true,
    // arrows: true,
  };
  addSlide() {}

  removeSlide() {}

  slickInit(e: any) {
    console.log('slick initialized');
  }

  breakpoint(e: any) {
    console.log('breakpoint');
  }

  afterChange(e: any) {
    console.log('afterChange');
  }

  beforeChange(e: any) {
    console.log('beforeChange');
  }
}
