import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DashboardStats } from '../models/dashboard-stats.model';
import { TechnicianTicketSummary } from '../models/technician-ticket-summary.model';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private apiUrl = 'http://localhost:8083/api/dashboard';

  constructor(private http: HttpClient) { }

  getStats(): Observable<DashboardStats> {
    return this.http.get<DashboardStats>(`${this.apiUrl}/stats`);
  }

  getTechnicianTicketSummary(): Observable<TechnicianTicketSummary[]> {
    return this.http.get<TechnicianTicketSummary[]>(`${this.apiUrl}/technician-summary`);
  }
}
