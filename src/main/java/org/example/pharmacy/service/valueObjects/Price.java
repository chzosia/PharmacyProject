package org.example.pharmacy.service.valueObjects;

public class Price {

    private final float value;


    public Price(float value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }


    public static Price create(float value) {
        if (value < 0) {
            throw new IllegalArgumentException("Price value cannot be negative");
        }

        var roundedValue = (float)(Math.round(value * 100) / 100);
        return new Price(roundedValue);
    }
}
