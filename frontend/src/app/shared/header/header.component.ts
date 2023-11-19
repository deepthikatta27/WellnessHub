import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
})
export class HeaderComponent implements OnInit {
  currentUrl = '';
  ngOnInit(): void {
    if (sessionStorage.getItem('user') != null) {
      this.visibleUser = true;
    }
    // get current url
    var url = window.location.href;
    var urlSplit = url.split('/');
    this.currentUrl = urlSplit[3];
  }
  visibleUser = false;
  signOut() {
    sessionStorage.clear();
    this.visibleUser = false;
    window.location.reload();
  }
}
