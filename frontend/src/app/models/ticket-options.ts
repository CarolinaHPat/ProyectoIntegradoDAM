export type TicketStatus = 'OPEN' | 'PENDING' | 'IN_PROGRESS' | 'CLOSED';
export type TicketPriority = 'LOW' | 'MEDIUM' | 'HIGH';

export const TICKET_STATUS_OPTIONS: Array<{ value: TicketStatus; label: string }> = [
  { value: 'OPEN', label: 'Abierto' },
  { value: 'PENDING', label: 'Pendiente' },
  { value: 'IN_PROGRESS', label: 'En curso' },
  { value: 'CLOSED', label: 'Cerrado' }
];

export const TICKET_PRIORITY_OPTIONS: Array<{ value: TicketPriority; label: string }> = [
  { value: 'LOW', label: 'Baja' },
  { value: 'MEDIUM', label: 'Media' },
  { value: 'HIGH', label: 'Alta' }
];

export const TICKET_STATUS_LABELS: Record<TicketStatus, string> = {
  OPEN: 'Abierto',
  PENDING: 'Pendiente',
  IN_PROGRESS: 'En curso',
  CLOSED: 'Cerrado'
};

export const TICKET_PRIORITY_LABELS: Record<TicketPriority, string> = {
  LOW: 'Baja',
  MEDIUM: 'Media',
  HIGH: 'Alta'
};
