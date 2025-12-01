package com.cau.dashboard.model.dto;

public class TechnicianTicketSummary {
    private String technicianName;
    private String specialty;
    private long ticketCount;

    public TechnicianTicketSummary() {
    }

    public TechnicianTicketSummary(String technicianName, String specialty, long ticketCount) {
        this.technicianName = technicianName;
        this.specialty = specialty;
        this.ticketCount = ticketCount;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public long getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(long ticketCount) {
        this.ticketCount = ticketCount;
    }
}
