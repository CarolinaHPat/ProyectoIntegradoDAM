package com.cau.dashboard.model.dto;

import com.cau.dashboard.model.Priority;
import com.cau.dashboard.model.Status;

public class TicketRequestDTO {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Long assignedTechnicianId;

    public TicketRequestDTO() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public Long getAssignedTechnicianId() { return assignedTechnicianId; }
    public void setAssignedTechnicianId(Long assignedTechnicianId) { this.assignedTechnicianId = assignedTechnicianId; }
}
