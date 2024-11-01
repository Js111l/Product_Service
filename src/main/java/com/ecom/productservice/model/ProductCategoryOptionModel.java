package com.ecom.productservice.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryOptionModel {
    private Long key;
    private String label;
    private String path;
    private Boolean checked;
    private List<ProductCategoryOptionModel> children;
}
