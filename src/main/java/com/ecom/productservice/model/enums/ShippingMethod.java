package com.ecom.productservice.model.enums;

import java.util.List;

public enum ShippingMethod implements BaseEnum {
    STANDARD_COURIER("Kurier"),
    PERSONAL_PICKUP("Odbiór osobisty");

    private final String label;

    ShippingMethod(String label) {
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
