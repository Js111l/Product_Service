package com.ecom.productservice.model.enums;

import java.util.List;

public enum ProductImportStatus implements BaseEnum{
    IMPORTED("Zaimportowano"),
    NOT_IMPORTED("Niezaimportowano"),
    ERROR("Blad");

    private final String label;

    ProductImportStatus(String label) {
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
