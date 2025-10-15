import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class Home {
  mythText: string = '';
  response: string = '';

  constructor(private http: HttpClient) {}

  bustMyth() {
    console.log("Button clicked"); // For debugging
    if (!this.mythText) return;

    this.http.post<any>('http://localhost:8080/api/myths/ask', { mythText: this.mythText }).subscribe({
      next: (res) => {
        this.response = res.aiResponse || 'No response from API';
      },
      error: (err) => {
        console.error(err);
        this.response = 'Error calling API';
      }
    });
  }
}
