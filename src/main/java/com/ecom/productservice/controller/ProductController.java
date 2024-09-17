package com.ecom.productservice.controller;

import com.ecom.productservice.criteria.ProductSearchCriteria;
import com.ecom.productservice.dao.repository.ProductRepository;
import com.ecom.productservice.model.ProductDashboardModel;
import com.ecom.productservice.model.ProductModel;
import com.ecom.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    @GetMapping("/dashboard")
    @CrossOrigin
    private List<ProductDashboardModel> productDashboardModels(
            @RequestParam("bestseller") Boolean bestseller
    ) throws InterruptedException {
        //TODO
        var sc = new ProductSearchCriteria(0, 40, true, "created_at", true);
        return productService.getDahboardModels(sc);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    private ProductModel getDetail(@PathVariable("id") Long id) {
        return productService.getDetail(id);
    }

}
