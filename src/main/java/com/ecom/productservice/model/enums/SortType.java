package com.ecom.productservice.model.enums;

import java.util.List;

public enum SortType implements BaseEnum {
    PRICE_ASC("Cena od najniższej"),
    PRICE_DESC("Cena od najwyższej"),
    POPULAR("Popularne"),
    NEWEST("Nowości");

    private final String label;

    SortType(String label) {
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

