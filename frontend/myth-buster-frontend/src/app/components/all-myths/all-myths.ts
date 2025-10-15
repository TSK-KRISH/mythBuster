import { CommonModule, DatePipe } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component,OnInit } from '@angular/core';

@Component({
  selector: 'app-all-myths',
  imports: [DatePipe,CommonModule,HttpClientModule],
  templateUrl: './all-myths.html',
  styleUrl: './all-myths.css'
})
export class AllMyths implements OnInit {
  myths: any[] = [];
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getAllMyths();
  }
  
  getAllMyths() {
  this.http.get<any[]>('http://localhost:8080/api/myths/history').subscribe({
    next: (res) => {
      this.myths = res || [];
      console.log('Fetched myths:', this.myths);
    },
    error: (err) => {
      console.error(err);
      this.myths = [];
    }
  });
}

}
