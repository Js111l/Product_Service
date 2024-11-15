package com.ecom.productservice.model.enums;

import java.util.List;

public enum Style implements BaseEnum {
    CASUAL("Casual"),
    FORMAL("Formalny"),
    SPORTS("Sportowy");

    private final String label;

    Style(String label) {
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
