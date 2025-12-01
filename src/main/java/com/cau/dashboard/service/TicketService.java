package com.cau.dashboard.service;

import com.cau.dashboard.model.Priority;
import com.cau.dashboard.model.Status;
import com.cau.dashboard.model.Ticket;
import com.cau.dashboard.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket saveTicket(Ticket ticket) {
        if (ticket.getOpeningDate() == null) {
            ticket.setOpeningDate(LocalDate.now());
        }
        if (ticket.getStatus() == Status.CLOSED && ticket.getClosingDate() == null) {
            ticket.setClosingDate(LocalDate.now());
        }
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<Ticket> getTicketsByAssignedTechnician(Long technicianId) {
        return ticketRepository.findByAssignedTechnician_Id(technicianId);
    }

    public List<Ticket> getTicketsByStatusAndPriority(Status status, Priority priority) {
        return ticketRepository.findByStatusAndPriority(status, priority);
    }

    public List<Ticket> getTicketsByStatus(Status status) {
        return ticketRepository.findByStatus(status);
    }

    public List<Ticket> getTicketsByPriority(Priority priority) {
        return ticketRepository.findByPriority(priority);
    }

    public List<Ticket> filterTickets(Status status, Priority priority, Long technicianId) {
        return ticketRepository.findAll().stream()
                .filter(t -> status == null || t.getStatus() == status)
                .filter(t -> priority == null || t.getPriority() == priority)
                .filter(t -> technicianId == null || (t.getAssignedTechnician() != null && t.getAssignedTechnician().getId().equals(technicianId)))
                .collect(java.util.stream.Collectors.toList());
    }
}
