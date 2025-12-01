export interface DashboardStats {
  openedToday: number;
  closedToday: number;
  averageResolutionTime: number;
  statusCounts: { [key: string]: number };
  reopenedTickets: number;
}
