import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterLink],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class RegisterComponent {
  user = {
    email: '',
    password: '',
    name: ''
  };
  error = '';
  private apiUrl = 'http://localhost:8080/api/auth/register';

  constructor(private router: Router, private http: HttpClient) {}

  onRegister() {
    this.error = '';

    // Validate all fields are filled
    if (!this.user.name || !this.user.email || !this.user.password) {
      this.error = 'Please fill in all fields';
      return;
    }

    // Validate name
    if (!this.user.name.trim()) {
      this.error = 'Please enter your name';
      return;
    }

    // Validate email format
    if (!this.isValidEmail(this.user.email)) {
      this.error = 'Please enter a valid email address';
      return;
    }

    // Validate password rules: min 8 chars, at least one uppercase, one lowercase, one number
    const pwd = this.user.password;
    if (pwd.length < 8) {
      this.error = 'Password must be at least 8 characters long';
      return;
    }
    if (!/[A-Z]/.test(pwd) || !/[a-z]/.test(pwd) || !/[0-9]/.test(pwd)) {
      this.error = 'Password must include uppercase, lowercase letters and a number';
      return;
    }

    // Make the HTTP request with response type 'text' since backend sends plain text
    this.http.post(this.apiUrl, this.user, { responseType: 'text' }).subscribe({
      next: (response) => {
        // Show success alert
        alert('Registration successful!');
        // Navigate to login page
        this.router.navigate(['/login']);
      },
      error: (err) => {
        // Handle error response
        if (err.error instanceof Error) {
          // Client-side error
          this.error = 'An error occurred while registering';
        } else {
          // Since we're using responseType: 'text', err.error contains the raw error message
          // from the backend like "Email already exists"
          // err.error may be a string; show it if present, otherwise fallback
          this.error = err.error || 'Registration failed';
        }
        console.error('Registration error:', err); // For debugging
      }
    });
  }

  private isValidEmail(email: string) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email.toLowerCase());
  }
}
