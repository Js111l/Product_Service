package com.ecom.productservice.dao.mapper;

import com.ecom.productservice.dao.entities.CheckoutProduct;
import com.ecom.productservice.dao.entities.Color;
import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.dao.entities.ProductCategory;
import com.ecom.productservice.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDashboardModel mapEntityToModel(Product entity);

    ProductModel mapEntityToDetailModel(Product entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    ProductCategoryModel mapEntityToProductCategoryModel(ProductCategory entity);

    @Mapping(source = "id", target = "key")
    @Mapping(source = "childCategories", target = "children")
    ProductCategoryOptionModel mapEntityToOptionModel(ProductCategory entity);

    default String mapColorToString(Color color) {
        return color != null ? color.getName() : null;  // Return the 'name' of the color, or null if color is null
    }

    CartProductModel mapEntityToCartModel(CheckoutProduct entity);
}
