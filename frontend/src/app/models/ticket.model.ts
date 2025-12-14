import type { TicketPriority, TicketStatus } from './ticket-options';

export interface Ticket {
  id?: number;
  title: string;
  description: string;
  status: TicketStatus;
  openingDate?: string;
  closingDate?: string;
  priority: TicketPriority;
  assignedTechnicianId: number | null;
}
