package com.ecom.productservice.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryModel {
    private Long id;
    private String label;
    private String path;
    private ProductCategoryModel parentCategory;
    //private List<ProductCategoryModel> childCategories;
}
