import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { TicketService } from '../../services/ticket';
import { TechnicianService } from '../../services/technician';
import { Ticket } from '../../models/ticket.model';
import { Technician } from '../../models/technician.model';

@Component({
  selector: 'app-ticket-list',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './ticket-list.html',
  styleUrls: ['./ticket-list.css']
})
export class TicketListComponent implements OnInit {

  tickets: Ticket[] = [];
  technicians: Technician[] = [];
  technicianMap: { [key: number]: string } = {};

  // Propiedades para el filtrado
  filterStatus = '';
  filterPriority = '';
  filterTechnicianId = '';

  constructor(
    private ticketService: TicketService,
    private technicianService: TechnicianService,
    private router: Router
  ) {
    console.log('Constructor ejecutado: Servicios inyectados.');
  }

  ngOnInit(): void {
    console.log('ngOnInit ejecutado: ¡El componente TicketListComponent se ha iniciado!');
    this.loadTechnicians();
    this.loadTickets();
  }

  loadTickets(): void {
    this.ticketService.getTickets().subscribe({
      next: data => {
        this.tickets = data;
        console.log('Tickets cargados con éxito:', data);
      },
      error: err => console.error('Error al cargar los tickets:', err)
    });
  }

  loadTechnicians(): void {
    this.technicianService.getTechnicians().subscribe({
      next: data => {
        this.technicians = data;
        this.technicianMap = data.reduce((map, tech) => {
          map[tech.id] = tech.name;
          return map;
        }, {} as { [key: number]: string });
        console.log('Técnicos cargados con éxito:', data);
      },
      error: err => console.error('Error al cargar los técnicos:', err)
    });
  }

  applyFilters(): void {
    this.ticketService.filterTickets(
      this.filterStatus || undefined,
      this.filterPriority || undefined,
      this.filterTechnicianId ? Number(this.filterTechnicianId) : undefined
    ).subscribe({
      next: data => {
        this.tickets = data;
        console.log('Tickets filtrados con éxito:', data);
      },
      error: err => console.error('Error al filtrar los tickets:', err)
    });
  }

  clearFilters(): void {
    this.filterStatus = '';
    this.filterPriority = '';
    this.filterTechnicianId = '';
    this.loadTickets();
  }

  deleteTicket(id: number): void {
    if (confirm('¿Estás seguro de que quieres eliminar este ticket?')) {
      this.ticketService.deleteTicket(id).subscribe({
        next: () => {
          this.loadTickets(); // Recargar la lista después de eliminar
          console.log('Ticket eliminado con éxito');
        },
        error: err => console.error('Error al eliminar el ticket:', err)
      });
    }
  }

  editTicket(id: number): void {
    this.router.navigate(['/main/tickets/edit', id]);
  }

  createNewTicket(): void {
    this.router.navigate(['/main/tickets/new']);
  }
}
