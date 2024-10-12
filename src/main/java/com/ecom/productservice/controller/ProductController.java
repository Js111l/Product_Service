package com.ecom.productservice.controller;

import com.ecom.productservice.criteria.ProductSearchCriteria;
import com.ecom.productservice.model.ProductDashboardModel;
import com.ecom.productservice.model.ProductModel;
import com.ecom.productservice.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private SecurityService securityService;
    private AsyncImportExecutor importExecutor;

    @GetMapping("/dashboard")
    @CrossOrigin
    private List<ProductDashboardModel> productDashboardModels(
            @RequestParam("bestseller") Boolean bestseller,
            HttpServletRequest servletRequest
    ) {
        //TODO
        var sc = new ProductSearchCriteria(0, 40, true, "created_at", true);
        return productService.getDahboardModels(sc);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    private ProductModel getDetail(@PathVariable("id") Long id) {
        return productService.getDetail(id);
    }

    @PostMapping("/upload")
    public CompletableFuture<ResponseEntity<Object>> importUsers(@RequestPart("productsCsv") MultipartFile csvFile,
                                                                 HttpServletRequest servletRequest) {
        final var currentUser = this.securityService.getCurrentUser(servletRequest.getHeader("Authorization"));
        return this.importExecutor.importProducts(csvFile, currentUser.email());
    }
}
