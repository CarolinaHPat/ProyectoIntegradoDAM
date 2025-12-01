package com.cau.dashboard.service;

import com.cau.dashboard.model.dto.Technician;
import com.cau.dashboard.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    public List<Technician> getAllTechnicians() {
        return technicianRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Technician convertToDto(com.cau.dashboard.model.Technician technician) {
        return new Technician(
                technician.getId(),
                technician.getName(),
                technician.getSpecialty()
        );
    }
}
