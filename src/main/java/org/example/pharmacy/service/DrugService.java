package org.example.pharmacy.service;

import org.example.pharmacy.controller.dto.DrugDto;
import org.example.pharmacy.infrastructure.entity.DrugEntity;
import org.example.pharmacy.infrastructure.repository.DrugRepository;
import org.example.pharmacy.service.model.DrugModel;
import org.example.pharmacy.service.valueObjects.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    private final DrugRepository drugRepository;

    @Autowired
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public List<DrugEntity> getAll() {
        return drugRepository.findAll();
    }

    public DrugEntity getOne (long id) {
        return drugRepository.findById(id).orElseThrow(() -> new RuntimeException("Drug not found"));

    }

    public DrugDto create(DrugEntity drug) {
        var price = Price.create((float) drug.getPrice());
        var drugModel = new DrugModel(null, drug.getName(), price, drug.getDose());

        var drugEntity = new DrugEntity();
        drugEntity.setName(drugModel.getName());
        drugEntity.setPrice(drugModel.getPrice().getValue());


        return drugRepository.save(drugDto);
    }

    public void delete(long id) {
        if(!drugRepository.existsById(id)){
            throw new RuntimeException();
        }
        drugRepository.deleteById(id);
    }
}
