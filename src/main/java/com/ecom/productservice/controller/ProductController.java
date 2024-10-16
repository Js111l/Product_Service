package com.ecom.productservice.controller;

import com.ecom.productservice.criteria.ProductSearchCriteria;
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

    @GetMapping("/categories/children")
    @CrossOrigin
    public List<ProductCategoryModel> getDirectChildren(Long parentId,
                                                        String parentPrefix,
                                                        Boolean firstLevel) {
        return this.productService.getChildrenCategories(parentId, parentPrefix, firstLevel);
    }
}







