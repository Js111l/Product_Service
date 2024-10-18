package com.ecom.productservice.model.enums;

public enum SortType {
    PRICE_ASC("Cena od najniższej"),
    PRICE_DESC("Cena od najwyższej"),
    POPULAR("Popularne"),
    NEWEST("Nowości");

    private final String label;

    SortType(String label) {
        this.label = label;
    }
}

