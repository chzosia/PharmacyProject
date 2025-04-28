package org.example.pharmacy.service.model;

import org.example.pharmacy.service.valueObjects.Price;

public class DrugModel {

    private Long id;
    private String code;
    private String name;
    private String manufacturer;
    private int availableUnits;
    private String dose;
    private String form;
    private Price price;
    private String symptom;

    public DrugModel(Long id, String code, String name, String manufacturer, int availableUnits,
                     String dose, String form, Price price, String symptom) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.manufacturer = manufacturer;
        this.availableUnits = availableUnits;
        this.dose = dose;
        this.form = form;
        this.price = price;
        this.symptom = symptom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
