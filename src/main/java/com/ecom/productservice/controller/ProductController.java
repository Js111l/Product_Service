package com.ecom.productservice.controller;

import com.ecom.productservice.criteria.ProductSearchCriteria;
import com.ecom.productservice.model.CartModel;
import com.ecom.productservice.model.CheckoutModel;
import com.ecom.productservice.model.ProductCategoryMenuBarModel;
import com.ecom.productservice.model.ProductCategoryModel;
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
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortAsc") Boolean sortAsc,
            @RequestParam("bestseller") Boolean bestseller,
            HttpServletRequest servletRequest
    ) {
        //TODO
        final var sc = ProductSearchCriteria.builder()
                .page(page)
                .size(size)
                .sortField(sortField)
                .sortAsc(sortAsc)
                .bestsellerOrNewFlag(bestseller)
                .build();
        return productService.getDahboardModels(sc);
    }

    @GetMapping("/list")
    @CrossOrigin
    private Page<ProductModel> getList(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortAsc") Boolean sortAsc,
            @RequestParam(value = "category", required = false) String categoryPath) {
        final var sc = ProductSearchCriteria.builder()
                .page(page)
                .size(size)
                .sortField(sortField)
                .sortAsc(sortAsc)
                .categoryPath(categoryPath)
                .build();

        return productService.getList(sc);
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

    @GetMapping("/categories/children")
    @CrossOrigin
    public List<ProductCategoryModel> getDirectChildren(Long parentId,
                                                        String parentPrefix,
                                                        Boolean firstLevel) {
        return this.productService.getChildrenCategories(parentId, parentPrefix, firstLevel);
    }

    @GetMapping("/categories/parents")
    @CrossOrigin
    public List<ProductCategoryModel> getParentCategories() {
        return this.productService.getParentCategories();
    }

    @GetMapping("/categories/menubar")
    @CrossOrigin
    public List<ProductCategoryMenuBarModel> getCategoriesForMenuBar() {
        return this.productService.getMenuBarCategories();
    }

}







