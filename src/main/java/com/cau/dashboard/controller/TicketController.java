package com.cau.dashboard.controller;

import com.cau.dashboard.model.Priority;
import com.cau.dashboard.model.Status;
import com.cau.dashboard.model.Ticket;
import com.cau.dashboard.model.dto.TicketRequestDTO;
import com.cau.dashboard.model.dto.TicketResponseDTO;
import com.cau.dashboard.repository.TechnicianRepository;
import com.cau.dashboard.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TechnicianRepository technicianRepository;

    @GetMapping
    public List<TicketResponseDTO> getAllTickets() {
        return ticketService.getAllTickets().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public TicketResponseDTO createTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = convertToEntity(ticketRequestDTO);
        ticket.setOpeningDate(LocalDate.now());
        Ticket createdTicket = ticketService.saveTicket(ticket);
        return convertToResponseDto(createdTicket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ticket -> ResponseEntity.ok(convertToResponseDto(ticket)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long id, @RequestBody TicketRequestDTO ticketRequestDTO) {
        return ticketService.getTicketById(id)
                .map(ticket -> {
                    ticket.setTitle(ticketRequestDTO.getTitle());
                    ticket.setDescription(ticketRequestDTO.getDescription());
                    ticket.setStatus(ticketRequestDTO.getStatus());
                    ticket.setPriority(ticketRequestDTO.getPriority());

                    if (ticketRequestDTO.getStatus() == Status.CLOSED) {
                        ticket.setClosingDate(LocalDate.now());
                    } else {
                        ticket.setClosingDate(null);
                    }

                    if (ticketRequestDTO.getAssignedTechnicianId() != null) {
                        technicianRepository.findById(ticketRequestDTO.getAssignedTechnicianId())
                                .ifPresent(ticket::setAssignedTechnician);
                    } else {
                        ticket.setAssignedTechnician(null);
                    }

                    Ticket updatedTicket = ticketService.saveTicket(ticket);
                    return ResponseEntity.ok(convertToResponseDto(updatedTicket));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ticket -> {
                    ticketService.deleteTicket(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public List<TicketResponseDTO> filterTickets(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Long technicianId) {

        List<Ticket> filteredTickets = ticketService.filterTickets(status, priority, technicianId);
        return filteredTickets.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private TicketResponseDTO convertToResponseDto(Ticket ticket) {
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setId(ticket.getId());
        ticketResponseDTO.setTitle(ticket.getTitle());
        ticketResponseDTO.setDescription(ticket.getDescription());
        ticketResponseDTO.setStatus(ticket.getStatus());
        ticketResponseDTO.setPriority(ticket.getPriority());
        ticketResponseDTO.setOpeningDate(ticket.getOpeningDate());
        ticketResponseDTO.setClosingDate(ticket.getClosingDate());
        if (ticket.getAssignedTechnician() != null) {
            ticketResponseDTO.setAssignedTechnicianId(ticket.getAssignedTechnician().getId());
            ticketResponseDTO.setAssignedTechnicianName(ticket.getAssignedTechnician().getName());
        }
        return ticketResponseDTO;
    }

    private Ticket convertToEntity(TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequestDTO.getTitle());
        ticket.setDescription(ticketRequestDTO.getDescription());
        ticket.setStatus(ticketRequestDTO.getStatus());
        ticket.setPriority(ticketRequestDTO.getPriority());
        if (ticketRequestDTO.getAssignedTechnicianId() != null) {
            technicianRepository.findById(ticketRequestDTO.getAssignedTechnicianId())
                    .ifPresent(ticket::setAssignedTechnician);
        }
        return ticket;
    }
}
