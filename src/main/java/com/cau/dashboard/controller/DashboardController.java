package com.cau.dashboard.controller;

import com.cau.dashboard.model.dto.DashboardStats;
import com.cau.dashboard.model.dto.TechnicianTicketSummary;
import com.cau.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8083"})
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public DashboardStats getDashboardStats() {
        return dashboardService.getDashboardStats();
    }

    @GetMapping("/technician-summary")
    public List<TechnicianTicketSummary> getTechnicianTicketSummary() {
        return dashboardService.getTechnicianTicketSummary();
    }
}
