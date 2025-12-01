package com.cau.dashboard.model.dto;

import java.util.Map;

public class DashboardStats {

    private long openedToday;
    private long closedToday;
    private double averageResolutionTime;
    private Map<String, Long> statusCounts;
    private long reopenedTickets; // Placeholder for now
    private long unassignedTickets;

    // Constructors
    public DashboardStats() {
    }

    public DashboardStats(long openedToday, long closedToday, double averageResolutionTime, Map<String, Long> statusCounts, long reopenedTickets, long unassignedTickets) {
        this.openedToday = openedToday;
        this.closedToday = closedToday;
        this.averageResolutionTime = averageResolutionTime;
        this.statusCounts = statusCounts;
        this.reopenedTickets = reopenedTickets;
        this.unassignedTickets = unassignedTickets;
    }

    // Getters and Setters
    public long getOpenedToday() {
        return openedToday;
    }

    public void setOpenedToday(long openedToday) {
        this.openedToday = openedToday;
    }

    public long getClosedToday() {
        return closedToday;
    }

    public void setClosedToday(long closedToday) {
        this.closedToday = closedToday;
    }

    public double getAverageResolutionTime() {
        return averageResolutionTime;
    }

    public void setAverageResolutionTime(double averageResolutionTime) {
        this.averageResolutionTime = averageResolutionTime;
    }

    public Map<String, Long> getStatusCounts() {
        return statusCounts;
    }

    public void setStatusCounts(Map<String, Long> statusCounts) {
        this.statusCounts = statusCounts;
    }

    public long getReopenedTickets() {
        return reopenedTickets;
    }

    public void setReopenedTickets(long reopenedTickets) {
        this.reopenedTickets = reopenedTickets;
    }

    public long getUnassignedTickets() {
        return unassignedTickets;
    }

    public void setUnassignedTickets(long unassignedTickets) {
        this.unassignedTickets = unassignedTickets;
    }
}
