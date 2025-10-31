import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterLink],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {

  // ✅ local user object (not from AuthService)
  user = {
    email: '',
    password: ''
  };

  error = '';
  private apiUrl = 'http://localhost:8080/api/auth/login';

  constructor(private router: Router, private http: HttpClient) {}

  onLogin() {
    this.error = '';

    // Client-side validation
    if (!this.user.email) {
      this.error = 'Please enter your email';
      return;
    }
    if (!this.isValidEmail(this.user.email)) {
      this.error = 'Please enter a valid email address';
      return;
    }
    if (!this.user.password) {
      this.error = 'Please enter your password';
      return;
    }

    this.http.post<any>(this.apiUrl, this.user).subscribe({
      next: (res) => {
        // Store the auth token
        localStorage.setItem('auth_token', res.token);
        console.log('Login success:', res);
        // Navigate to the ask page (which serves as our home)
        this.router.navigate(['/ask']);
      },
      error: (err) => {
        console.error('Login error:', err);
        // Backend may return a string or an object. Prefer readable messages.
        this.error = (err.error && typeof err.error === 'string') ? err.error : (err.error?.message || 'Invalid email or password');
      }
    });
  }

  private isValidEmail(email: string) {
    // Basic RFC-like email validation
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email.toLowerCase());
  }
}
