package com.ecom.productservice.model;

import com.ecom.productservice.model.enums.ProductStatus;
import com.ecom.productservice.model.enums.ProductStockStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductModel {
    private Long id;
    private String name;
    private String description;
    private List<String> sizes;
    private List<String> colorImgUrls;
    private BigDecimal price;
    private Long quantity; //quantity
    private ProductStockStatus stockStatus; //TODO
    private ProductStatus productStatus;
    private String color; //todo colors
    private String imageUrl;
    private String detailUrl;

}
