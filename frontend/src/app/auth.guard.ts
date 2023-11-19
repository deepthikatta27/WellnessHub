import { CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  if (
    sessionStorage.getItem('user') === '' ||
    sessionStorage.getItem('user') === undefined ||
    sessionStorage.getItem('user') === null
  ) {
    window.location.href = '/login';
    return false;
  } else {
    return true;
  }
};
