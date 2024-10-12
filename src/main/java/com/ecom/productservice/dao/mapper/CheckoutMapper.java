package com.ecom.productservice.dao.mapper;

import com.ecom.productservice.dao.entities.CheckoutProduct;
import com.ecom.productservice.model.CheckoutModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CheckoutMapper {
    CheckoutMapper INSTANCE = Mappers.getMapper(CheckoutMapper.class);

    CheckoutProduct mapModelToEntity(CheckoutModel model);
}
