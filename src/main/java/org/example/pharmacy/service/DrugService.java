package org.example.pharmacy.service;

import org.example.pharmacy.controller.dto.DrugResponseDto;
import org.example.pharmacy.infrastructure.entity.DrugEntity;
import org.example.pharmacy.infrastructure.repository.IDrugRepository;
import org.example.pharmacy.service.errors.DrugNotFoundError;
import org.example.pharmacy.service.valueObjects.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    private final IDrugRepository drugRepository;

    @Autowired
    public DrugService(IDrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public List<DrugEntity> getAll() {
        return drugRepository.findAll();
    }

    public DrugEntity getOne (long id) {
        return drugRepository.findById(id).orElseThrow(() -> new DrugNotFoundError());

    }

    public DrugResponseDto create(DrugEntity drug) {
        var price = Price.create(drug.getPrice().floatValue());


        var drugEntity = new DrugEntity();
        drugEntity.setCode(drug.getCode());
        drugEntity.setName(drug.getName());
        drugEntity.setManufacturer(drug.getManufacturer());
        drugEntity.setAvailableUnits(drug.getAvailableUnits());
        drugEntity.setDose(drug.getDose());
        drugEntity.setForm(drug.getForm());
        drugEntity.setPrice(price.getValue());
        drugEntity.setSymptom(drug.getSymptom());


        drugRepository.save(drugEntity);

        return new DrugResponseDto(
                drugEntity.getId(),
                drugEntity.getCode(),
                drugEntity.getName(),
                drugEntity.getManufacturer(),
                drugEntity.getAvailableUnits(),
                drugEntity.getDose(),
                drugEntity.getForm(),
                drugEntity.getPrice(),
                drugEntity.getSymptom()
        );
    }

    public void delete(long id) {
        if(!drugRepository.existsById(id)){
            throw new DrugNotFoundError();
        }
        drugRepository.deleteById(id);
    }

    public DrugEntity update(long id, DrugEntity drug) {
        DrugEntity existingDrug = drugRepository.findById(id)
                .orElseThrow(() -> new DrugNotFoundError());

        // Only update the fields that are not null in the request
        if (drug.getCode() != null) existingDrug.setCode(drug.getCode());
        if (drug.getName() != null) existingDrug.setName(drug.getName());
        if (drug.getManufacturer() != null) existingDrug.setManufacturer(drug.getManufacturer());
        if (drug.getAvailableUnits() != null) existingDrug.setAvailableUnits(drug.getAvailableUnits());
        if (drug.getDose() != null) existingDrug.setDose(drug.getDose());
        if (drug.getForm() != null) existingDrug.setForm(drug.getForm());
        if (drug.getPrice() != null)
            existingDrug.setPrice(Price.create(drug.getPrice().floatValue()).getValue());
        if (drug.getSymptom() != null) existingDrug.setSymptom(drug.getSymptom());

        return drugRepository.save(existingDrug);
    }
}
