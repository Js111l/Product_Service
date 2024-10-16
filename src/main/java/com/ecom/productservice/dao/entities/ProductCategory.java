package com.ecom.productservice.dao.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "PRODUCT_CATEGORY")
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"childCategories", "parentCategory"})
public class ProductCategory extends BaseEntity {
    @Column(name = "path")
    private String path; //hierarchical path. for example electronics.phones
    @Column(name = "label")
    private String label; //label, like Phones, Jeans,
    @ManyToOne
    @JoinColumn(name = "parent_category_id", updatable = false)
    private ProductCategory parentCategory;
    @OneToMany(mappedBy="parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategory> childCategories = new ArrayList<>();
}
