package com.springcourse.project.controller;

import com.springcourse.project.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;
}
