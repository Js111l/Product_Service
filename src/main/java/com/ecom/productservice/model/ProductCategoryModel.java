package com.ecom.productservice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"childCategories", "parentCategoryId"})
@ToString(exclude = {"childCategories", "parentCategoryId"})
public class ProductCategoryModel {
    private Long id;
    private String label;
    private String path;
    private Long parentCategoryId;
    private List<ProductCategoryModel> childCategories;
}
