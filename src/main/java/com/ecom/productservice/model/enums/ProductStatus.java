package com.ecom.productservice.model.enums;

import java.util.List;

public enum ProductStatus implements BaseEnum {
    ACTIVE("Aktywny"),
    NOT_ACTIVE("Nieaktywny");

    private final String label;

    ProductStatus(String label) {
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
