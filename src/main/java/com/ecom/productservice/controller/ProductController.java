package com.ecom.productservice.controller;

import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.model.ProductDashboardModel;
import com.ecom.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/dashboard")
    private List<ProductDashboardModel> productDashboardModels(
            @RequestParam("bestseller") Boolean bestseller) {
        //TODO
        return List.of();
    }
}
