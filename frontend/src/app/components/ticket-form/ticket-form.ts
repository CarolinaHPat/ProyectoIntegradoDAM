import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { Ticket } from '../../models/ticket.model';
import { Technician } from '../../models/technician.model';
import { TicketService } from '../../services/ticket';
import { TechnicianService } from '../../services/technician';

@Component({
  selector: 'app-ticket-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ticket-form.html',
  styleUrls: ['./ticket-form.css']
})
export class TicketFormComponent implements OnInit {

  ticket: Ticket = {
    id: 0,
    title: '',
    description: '',
    status: 'OPEN',
    priority: 'MEDIUM',
    assignedTechnicianId: null
  };
  technicians: Technician[] = [];
  isEditMode = false;
  private ticketId: number | null = null;

  constructor(
    private ticketService: TicketService,
    private technicianService: TechnicianService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadTechnicians();

    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEditMode = true;
        this.ticketId = +id;
        this.ticketService.getTicket(this.ticketId).subscribe((data: Ticket) => {
          this.ticket = data;
        });
      }
    });
  }

  loadTechnicians(): void {
    this.technicianService.getTechnicians().subscribe(data => {
      this.technicians = data;
    });
  }

  saveTicket(): void {
    if (this.isEditMode && this.ticketId) {
      this.ticketService.updateTicket(this.ticketId, this.ticket).subscribe(() => {
        this.router.navigate(['/main/tickets']);
      });
    } else {
      this.ticketService.createTicket(this.ticket).subscribe(() => {
        this.router.navigate(['/main/tickets']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/main/tickets']);
  }
}
