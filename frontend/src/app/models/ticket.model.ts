export interface Ticket {
  id?: number;
  title: string;
  description: string;
  status: 'OPEN' | 'IN_PROGRESS' | 'CLOSED';
  openingDate?: string; 
  closingDate?: string;
  priority: 'LOW' | 'MEDIUM' | 'HIGH';
  assignedTechnicianId: number | null;
}
