package com.cau.dashboard.service;

import com.cau.dashboard.model.Status;
import com.cau.dashboard.model.Ticket;
import com.cau.dashboard.model.Technician;
import com.cau.dashboard.model.dto.DashboardStats;
import com.cau.dashboard.model.dto.TechnicianTicketSummary;
import com.cau.dashboard.repository.TechnicianRepository;
import com.cau.dashboard.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private static final Logger log = LoggerFactory.getLogger(DashboardService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    public DashboardStats getDashboardStats() {
        LocalDate today = LocalDate.now();

        long openedToday = ticketRepository.countByOpeningDate(today);
        long closedToday = ticketRepository.countByClosingDate(today);

        Map<String, Long> statusCounts = Arrays.stream(Status.values())
                .collect(Collectors.toMap(Enum::name, status -> ticketRepository.countByStatus(status)));

        double avgResolutionTime = calculateAverageResolutionTime();

        // El número de tickets reabiertos se deja como placeholder por ahora
        long reopenedTickets = 0; 

        long unassignedTickets = ticketRepository.countByAssignedTechnicianIsNull();

        return new DashboardStats(openedToday, closedToday, avgResolutionTime, statusCounts, reopenedTickets, unassignedTickets);
    }

    public List<TechnicianTicketSummary> getTechnicianTicketSummary() {
        List<Technician> technicians = technicianRepository.findAll();
        log.info("Found {} technicians.", technicians.size());

        List<TechnicianTicketSummary> summaries = new java.util.ArrayList<>();
        for (Technician technician : technicians) {
            long ticketCount = ticketRepository.countByAssignedTechnician(technician);
            log.info("Technician '{}' has {} tickets.", technician.getName(), ticketCount);
            summaries.add(new TechnicianTicketSummary(technician.getName(), technician.getSpecialty(), ticketCount));
        }
        return summaries;
    }

    private double calculateAverageResolutionTime() {
        List<Ticket> closedTickets = ticketRepository.findByStatus(Status.CLOSED);
        if (closedTickets.isEmpty()) {
            return 0.0;
        }

        long totalDays = closedTickets.stream()
                .filter(t -> t.getOpeningDate() != null && t.getClosingDate() != null)
                .mapToLong(t -> ChronoUnit.DAYS.between(t.getOpeningDate(), t.getClosingDate()))
                .sum();

        return (double) totalDays / closedTickets.size();
    }
}
