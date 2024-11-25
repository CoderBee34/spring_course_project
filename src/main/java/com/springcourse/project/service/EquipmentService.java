package com.springcourse.project.service;

import com.springcourse.project.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
}
