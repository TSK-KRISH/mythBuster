import { inject } from '@angular/core';
import { Router } from '@angular/router';

export const authGuard = () => {
  const router = inject(Router);
  
  // Check if auth token exists
  const isAuthenticated = localStorage.getItem('auth_token') !== null;
  
  if (!isAuthenticated) {
    // Not logged in, redirect to login page
    router.navigate(['/login']);
    return false;
  }
  
  // Logged in, allow access
  return true;
};