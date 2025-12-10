package com.cau.dashboard.controller;

import com.cau.dashboard.model.dto.Technician;
import com.cau.dashboard.service.TechnicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8083"})
@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    @GetMapping
    public List<Technician> getAllTechnicians() {
        System.out.println("\n\n[DEBUG] ¡Llamada recibida en TechnicianController para obtener todos los técnicos!\n\n");
        return technicianService.getAllTechnicians();
    }
}
