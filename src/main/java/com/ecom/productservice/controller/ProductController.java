package com.ecom.productservice.controller;

import com.ecom.productservice.criteria.ProductSearchCriteria;
import com.ecom.productservice.model.*;
import com.ecom.productservice.service.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
    private List<ProductDashboardModel> productDashboardModels(
//            @RequestParam(value = "page") Integer page,
//            @RequestParam(value = "size") Integer size,
//            @RequestParam(value = "sortField") String sortField,
//            @RequestParam(value = "sortAsc") Boolean sortAsc,
//            @RequestParam("bestseller") Boolean bestseller,
            HttpServletRequest servletRequest
    ) {
        //TODO
        final var sc = ProductSearchCriteria.builder()
//                .page(page)
//                .size(size)
//                .sortField(sortField)
//                .sortAsc(sortAsc)
//                .bestsellerOrNewFlag(bestseller)
                .page(0)
                .size(20)
                .sortField("id")
                .sortAsc(true)
                .bestsellerOrNewFlag(null)
                .build();
        return productService.getDahboardModels(sc);
    }

    @GetMapping("/list")
    private Page<ProductModel> getList(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortAsc") Boolean sortAsc,
            @RequestParam(value = "categories", required = false) List<Long> categories) {
        final var sc = ProductSearchCriteria.builder()
                .page(page)
                .size(size)
                .sortField(sortField)
                .sortAsc(sortAsc)
                //.categoryPath(categoryPath)
                .categories(categories)
                .build();

        return productService.getList(sc);
    }

    @GetMapping("/list/user-favorite")
    private Page<ProductModel> getFavoriteProducts(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortAsc") Boolean sortAsc,
            @RequestParam(value = "categories", required = false) List<Long> categories,
            HttpServletRequest servletRequest) {
        var sessionId = "";
        for (Cookie cookieElement : servletRequest.getCookies()) {
            if (cookieElement.getName().equals("sessionId")) {
                sessionId = cookieElement.getValue();
                break;
            }
        }

        final var sc = ProductSearchCriteria.builder()
                .page(page)
                .size(size)
                .sortField(sortField)
                .sortAsc(sortAsc)
                .categories(categories)
                .sessionId(sessionId)
                .getFavoriteList(true)
                .build();

        return productService.getList(sc);
    }

    @PostMapping("/user-favorite")
    private ResponseEntity<HttpStatus> addProductToFavorite(@RequestBody UserProductRequestModel requestModel, HttpServletRequest servletRequest) {
        String sessionId = null;
        for (Cookie cookieElement : servletRequest.getCookies()) {
            if (cookieElement.getName().equals("sessionId")) {
                sessionId = cookieElement.getValue();
                break;
            }
        }
        productService.addUserProduct(requestModel, sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/list/returns")
    private Page<ReturnListModel> getReturns(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "sortField") String sortField,
            @RequestParam(value = "sortAsc") Boolean sortAsc) {
        final var sc = ProductSearchCriteria.builder()
                .page(page)
                .size(size)
                .sortField(sortField)
                .sortAsc(sortAsc)
                .build();

        return productService.getReturns(sc);
    }

    @GetMapping("/{id}")
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
    public void addCheckoutProducts(@RequestBody CheckoutModel model,
                                    HttpServletRequest servletRequest) {
        String sessionId = null;
        for (Cookie cookieElement : servletRequest.getCookies()) {
            if (cookieElement.getName().equals("sessionId")) {
                sessionId = cookieElement.getValue();
                break;
            }
        }
        this.checkoutService.add(model, sessionId);
    }

    @DeleteMapping("/user-checkout")
    public void deleteCheckoutProducts(@RequestParam(value = "productIds") List<Long> productIds,
                                    HttpServletRequest servletRequest) {
        String sessionId = null;
        for (Cookie cookieElement : servletRequest.getCookies()) {
            if (cookieElement.getName().equals("sessionId")) {
                sessionId = cookieElement.getValue();
                break;
            }
        }
        this.checkoutService.delete(productIds, sessionId);
    }

    @GetMapping("/user-checkout/products")
    public CartModel getProducts(HttpServletRequest servletRequest) {
        String sessionId = null;
        for (Cookie cookieElement : servletRequest.getCookies()) {
            if (cookieElement.getName().equals("sessionId")) {
                sessionId = cookieElement.getValue();
                break;
            }
        }
        return this.checkoutService.getProductsFromCheckout(sessionId);
    }


    @PostMapping("/user-checkout/products")
    public void setCheckoutProductQuantity(//@PathVariable("id") Long id,
                                           @RequestParam("productId") Long id,
                                           @RequestParam("quantity") Long value, HttpServletRequest servletRequest) {
        String sessionId = null;
        for (Cookie cookieElement : servletRequest.getCookies()) {
            if (cookieElement.getName().equals("sessionId")) {
                sessionId = cookieElement.getValue();
                break;
            }
        }
        this.checkoutService.setQuantity(id, value, sessionId);
    }


    @GetMapping("/user-checkout")
    private CheckoutCountModel getCheckoutCount(HttpServletRequest servletRequest) {
        String sessionId = null;
        for (Cookie cookieElement : servletRequest.getCookies()) {
            if (cookieElement.getName().equals("sessionId")) {
                sessionId = cookieElement.getValue();
                break;
            }
        }
        return new CheckoutCountModel(
                this.checkoutService.getCheckoutCount(sessionId)
        );
    }

    @GetMapping("/categories/children")
    public List<ProductCategoryModel> getDirectChildren(@RequestParam("parentPath") String parentPrefix,
                                                        @RequestParam("firstLevel") Boolean firstLevel) {
        return this.productService.getChildrenCategories(parentPrefix, firstLevel);
    }

    @GetMapping("/categories/parents")
    public List<ProductCategoryModel> getParentCategories() {
        return this.productService.getParentCategories();
    }

    @GetMapping("/categories/parents/options")
    public List<ProductCategoryOptionModel> getParentCategoriesOptionModel() {
        return this.productService.getParentCategoriesOptionModels();
    }

    @GetMapping("/categories/menubar")
    public List<ProductCategoryMenuBarModel> getCategoriesForMenuBar() {
        return this.productService.getMenuBarCategories();
    }
}







