package com.ecom.productservice.dao.mapper;

import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.dao.entities.ProductCategory;
import com.ecom.productservice.model.ProductCategoryModel;
import com.ecom.productservice.model.ProductDashboardModel;
import com.ecom.productservice.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDashboardModel mapEntityToModel(Product entity);

    ProductModel mapEntityToDetailModel(Product entity);

    @Mapping(source = "id", target = "id")
    ProductCategoryModel mapEntityToProductCategoryModel(ProductCategory entity);
}
