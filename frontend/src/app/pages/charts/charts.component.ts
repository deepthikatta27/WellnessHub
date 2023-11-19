import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../shared/header/header.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { Chart, registerables } from 'chart.js';
import { HttpClient, HttpClientModule } from '@angular/common/http';
Chart.register(...registerables);
@Component({
  selector: 'app-charts',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent, HttpClientModule],
  templateUrl: './charts.component.html',
  styleUrl: './charts.component.css',
})
export class ChartsComponent implements OnInit {
  constructor(private api: HttpClient) {}

  ngOnInit(): void {
    this.renderChart();
    this.renderChartTwo();
  }

  renderChart() {
    const user = JSON.parse(sessionStorage.getItem('user') || '{}');

    this.api
      .get(`http://localhost:8080/userFoodTracking/chart/${user._id}`)
      .subscribe({
        next: (res: any) => {
          new Chart('barchart', {
            type: 'bar',
            data: {
              labels: res.map((item: any) => item.date),
              datasets: [
                {
                  label: 'Total Calories Consumed',
                  data: res.map((item: any) => item.calories),
                  borderWidth: 1,
                },
              ],
            },
            options: {
              scales: {
                y: {
                  beginAtZero: true,
                },
              },
            },
          });
        },
        error: (err) => {},
      });
  }

  renderChartTwo() {
    const user = JSON.parse(sessionStorage.getItem('user') || '{}');

    this.api
      .get(`http://localhost:8080/userExerciseTracking/chart/${user._id}`)
      .subscribe({
        next: (res: any) => {
          new Chart('exercisechart', {
            type: 'bar',
            data: {
              labels: res.map((item: any) => item.date),
              datasets: [
                {
                  label: 'Total Calories Burned',
                  data: res.map((item: any) => item.calories),
                  borderWidth: 1,
                },
              ],
            },
            options: {
              scales: {
                y: {
                  beginAtZero: true,
                },
              },
            },
          });
        },
        error: (err) => {},
      });
  }
}
