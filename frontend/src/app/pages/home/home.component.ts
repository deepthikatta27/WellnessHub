import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
// import { ToastrService } from 'ngx-toastr';
// import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { RouterLink } from '@angular/router';
import { SlickCarouselModule } from 'ngx-slick-carousel';
// import {
//   ReactiveFormsModule,
//   FormsModule,
//   FormGroup,
//   FormControl,
// } from '@angular/forms';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    HeaderComponent,
    FooterComponent,
    RouterLink,
    SlickCarouselModule,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  constructor() {}

  slideConfig = {
    slidesToShow: 1,
    slidesToScroll: 1,
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
