package com.ecom.productservice.controller;

import com.ecom.productservice.criteria.ProductSearchCriteria;
import com.ecom.productservice.model.CartModel;
import com.ecom.productservice.model.CheckoutModel;
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
    private final CheckoutService checkoutService;
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

    @PostMapping("/user-checkout")
    @CrossOrigin
    public void addCheckoutProducts(@RequestBody CheckoutModel model) {
        this.checkoutService.add(model);
    }
    @GetMapping("/user-checkout/products")
    @CrossOrigin
    public CartModel getProducts(HttpServletRequest servletRequest) {
        // var currentUser = this.securityService.getCurrentUser(servletRequest.getHeader("Authorization"));
        return this.checkoutService.getProductsFromCheckout(1L);
    }



    @PostMapping("/user-checkout/products")
    @CrossOrigin
    public void setCheckoutProductQuantity(//@PathVariable("id") Long id,
                                           @RequestParam("productId") Long id,
                                           @RequestParam("quantity") Long value, HttpServletRequest servletRequest) {
        //final var currentUser = this.securityService.getCurrentUser(servletRequest.getHeader("Authorization"));
        this.checkoutService.setQuantity(id, value, 1L);
    }



    @GetMapping("/user-checkout/{userId}")
    @CrossOrigin
    private Long getCheckoutCount(@PathVariable("userId") Long userId) {
        return this.checkoutService.getCheckoutCount(userId);
    }
}
