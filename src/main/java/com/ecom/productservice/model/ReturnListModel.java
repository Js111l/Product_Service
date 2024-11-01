package com.ecom.productservice.model;

import com.ecom.productservice.model.enums.ReturnStatus;

import java.time.LocalDate;

public class ReturnListModel {
    private LocalDate returnCreated;
    private Long totalReturned;
    private ReturnStatus returnStatus;
    private SimpleProductModel productModel;

    public record SimpleProductModel(Long id, String name, String color, String size, String thumbnailUrl) {
    }
}
