package com.ecom.productservice.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryMenuBarModel {
    private Long id;
    private String label;
    private String path;
    private List<ProductCategoryModel> firstLevelChildren;
}
