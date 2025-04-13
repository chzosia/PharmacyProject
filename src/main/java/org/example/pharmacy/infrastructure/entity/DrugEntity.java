package org.example.pharmacy.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "drugs", schema = "pharmacy")
public class DrugEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "code", unique = true)
    private String code;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "manufacturer")
    private String manufacturer;

    @Basic
    @Column(name = "available_units")
    private int availableUnits;

    @Basic
    @Column(name = "dose")
    private String dose;

    @Basic
    @Column(name = "form") // e.g., Tablet, Syrup, Injection
    private String form;

    @Basic
    @Column(name = "price")
    private double price;

    @Basic
    @Column(name = "symptom")
    private String symptom;


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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}

