import { Routes } from '@angular/router';
import { HomeComponent } from '../app/pages/home/home.component';
import { LoginComponent } from '../app/pages/login/login.component';
import { ChartsComponent } from './pages/charts/charts.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RecommendationsComponent } from './pages/recommendations/recommendations.component';
import { TrackingComponent } from './pages/tracking/tracking.component';
import { AdminloginComponent } from './admin/adminlogin/adminlogin.component';
import { ManageusersComponent } from './admin/manageusers/manageusers.component';
import { ManagefoodrecommendationsComponent } from './admin/managefoodrecommendations/managefoodrecommendations.component';
import { ManageexcersicerecommendationsComponent } from './admin/manageexcersicerecommendations/manageexcersicerecommendations.component';
import { authGuard } from './auth.guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'charts', component: ChartsComponent, canActivate: [authGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [authGuard] },
  {
    path: 'recommendations',
    component: RecommendationsComponent,
    canActivate: [authGuard],
  },
  { path: 'tracking', component: TrackingComponent, canActivate: [authGuard] },
  {
    path: 'admin',
    children: [
      { path: 'login', component: AdminloginComponent },
      { path: 'manageusers', component: ManageusersComponent },
      {
        path: 'managefoodrecommendations',
        component: ManagefoodrecommendationsComponent,
      },
      {
        path: 'manageexcersicerecommendations',
        component: ManageexcersicerecommendationsComponent,
      },
    ],
  },
];
