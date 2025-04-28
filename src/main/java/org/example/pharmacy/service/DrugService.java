package org.example.pharmacy.service;

import org.example.pharmacy.controller.dto.DrugResponseDto;
import org.example.pharmacy.infrastructure.entity.DrugEntity;
import org.example.pharmacy.infrastructure.repository.IDrugRepository;
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

    //TODO: change the returned value
    public DrugEntity getOne (long id) {
        return drugRepository.findById(id).orElseThrow(() -> new RuntimeException("Drug not found"));

    }

    public DrugResponseDto create(DrugEntity drug) {
        var price = Price.create((float) drug.getPrice());


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
            throw new RuntimeException();
        }
        drugRepository.deleteById(id);
    }
}
