package com.ecom.productservice.model.enums;

import java.util.List;

public enum SleeveLength implements BaseEnum {
    SHORT("Krotki"),
    LONG("Dlugi"),
    SLEEVELESS("Bez rekawow");

    private final String label;

    SleeveLength(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
    @Override
    public List<String> getValues() {
        return this.getValues();
    }
}

