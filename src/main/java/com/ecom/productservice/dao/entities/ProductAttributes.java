package com.ecom.productservice.dao.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductAttributes {
    private String brand;
    private String color;
    private String fabric;
    private BigDecimal price;
    private String sleeveLength;
    private String style;
}
