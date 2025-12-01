package com.cau.dashboard.repository;

import com.cau.dashboard.model.Priority;
import com.cau.dashboard.model.Status;
import com.cau.dashboard.model.Ticket;
import com.cau.dashboard.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Métodos para los KPIs del dashboard
    long countByStatus(Status status);
    long countByOpeningDate(LocalDate date);
    long countByClosingDate(LocalDate date);
    long countByAssignedTechnician(Technician technician);
    long countByAssignedTechnicianIsNull();

    // Métodos para filtrar tickets
    List<Ticket> findByStatus(Status status);
    List<Ticket> findByPriority(Priority priority);
    List<Ticket> findByAssignedTechnician_Id(Long technicianId);
    List<Ticket> findByStatusAndPriority(Status status, Priority priority);
    List<Ticket> findByStatusAndPriorityAndAssignedTechnician_Id(Status status, Priority priority, Long technicianId);
}
