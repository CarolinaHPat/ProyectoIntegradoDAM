import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../services/dashboard';
import { DashboardStats } from '../../models/dashboard-stats.model';
import { CommonModule } from '@angular/common';
import { TechnicianTicketSummary } from '../../models/technician-ticket-summary.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class DashboardComponent implements OnInit {

  stats?: DashboardStats;
  technicianSummary: TechnicianTicketSummary[] = [];

  constructor(
    private dashboardService: DashboardService
  ) { }

  ngOnInit(): void {
    this.loadStats();
    this.loadTechnicianSummary();
  }

  loadStats(): void {
    this.dashboardService.getStats().subscribe(data => {
      this.stats = data;
    });
  }

  loadTechnicianSummary(): void {
    this.dashboardService.getTechnicianTicketSummary().subscribe(data => {
      this.technicianSummary = data;
    });
  }

  getTotalTickets(): number {
    if (!this.stats || !this.stats.statusCounts) {
      return 0;
    }
    // Suma todos los valores del objeto statusCounts
    return Object.values(this.stats.statusCounts).reduce((sum, count) => sum + count, 0);
  }
}
