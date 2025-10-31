import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css']
})
export class Navbar {
  constructor(private router: Router) {}

  logout() {
    // Clear any stored authentication tokens
    localStorage.removeItem('auth_token');
    
    // Navigate to login page
    this.router.navigate(['/login']);
  }
}
