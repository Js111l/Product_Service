package com.ecom.productservice.model.enums;

import java.util.List;

public enum ProductStockStatus implements BaseEnum{
    OUT_OF_STOCK,
    LOW_STOCK,
    IN_STOCK;

    @Override
    public String getLabel() {
        return "";
    }
    @Override
    public List<String> getValues() {
        return this.getValues();
    }
}
