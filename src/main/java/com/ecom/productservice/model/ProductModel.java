package com.ecom.productservice.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductModel {
    private long id;
    private String name;
    private String description;
//    private LocalDateTime createdAt;
//    private LocalDateTime startDateOfSale;
//    private LocalDateTime endDateOfSale;
//    //private SellerModel sellerModel;
}
