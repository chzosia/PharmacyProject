package org.example.pharmacy.controller;

import org.example.pharmacy.controller.dto.DrugResponseDto;
import org.example.pharmacy.infrastructure.entity.DrugEntity;
import org.example.pharmacy.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
@PreAuthorize("isAuthenticated()")
public class DrugController {
    private final DrugService drugService;

    @Autowired
    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public List<DrugEntity> getALlDrugs() {
        return drugService.getAll();
    }

    @GetMapping("/{id}")
    public DrugResponseDto getOne(@PathVariable long id) {
        var drug = drugService.getOne(id);
        return new DrugResponseDto(drug.getId(), drug.getCode(), drug.getName(), drug.getManufacturer(), drug.getAvailableUnits(), drug.getDose(), drug.getForm(), drug.getPrice(), drug.getSymptom());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DrugResponseDto create(@Validated @RequestBody DrugEntity drug) {
        return drugService.create(drug);
    }

}
