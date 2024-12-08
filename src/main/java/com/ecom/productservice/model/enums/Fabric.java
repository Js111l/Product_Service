package com.ecom.productservice.model.enums;

import java.util.List;

public enum Fabric implements BaseEnum {
    COTTON("Bawelna"),
    POLYESTER("Poliester"),
    WOOL("Welna"),
    LINEN("Len"),
    DENIM("Jeans"),
    SILK("Jedwab"),
    LEATHER("Skóra")
;
    private final String label;

    Fabric(String label) {
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
