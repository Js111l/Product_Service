package com.ecom.productservice.service;

import com.ecom.productservice.criteria.ProductSearchCriteria;
import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.dao.mapper.ProductMapper;
import com.ecom.productservice.dao.repository.ProductRepository;
import com.ecom.productservice.dao.repository.UserProductRepository;
import com.ecom.productservice.exception.ErrorKey;
import com.ecom.productservice.exception.LogicalException;
import com.ecom.productservice.model.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductService implements BaseService<Product, Long> {

    private final ProductRepository productRepository;
    private final UserProductRepository userProductRepository;

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return this.productRepository
                .findById(id)
                .orElseThrow(() -> new LogicalException(ErrorKey.NOT_FOUND));
    }

    @Override
    public Product deleteById(Long id) {
        var productToDelete = getById(id);

        return productToDelete;
    }

    @Override
    public Product delete(Product productEntity) {
        this.productRepository.delete(productEntity);
        return productEntity; //todo
    }

    @Override
    public Product create(Product productEntity) {
        return this.productRepository.save(productEntity);
    }

    @Override
    public Product update(Product productEntity) {
        return null;
    }

    public List<ProductDashboardModel> getDahboardModels(ProductSearchCriteria searchCriteria) {
        return this.productRepository.findAll(searchCriteria.getSpecification())
                .stream()
                .map(ProductMapper.INSTANCE::mapEntityToModel)
                .toList();
    }
    public ProductModel getDetail(Long id) {
        var result = this.productRepository.findById(id).orElseThrow();
        var model = ProductMapper.INSTANCE.mapEntityToDetailModel(result);
        final var mockSizes = List.of("S", "M", "L", "XL");
        final var mockColorUrls = List.of("/jeans.jpg");
        final var mockImages = List.of(
                new ImageModel(null, "/jeans.jpg", "", ""),
                new ImageModel(null, "/jeans.jpg", "", ""),
                new ImageModel(null, "/jeans.jpg", "", "")
        );
        model.setSizes(mockSizes);
        model.setColorImgUrls(mockColorUrls);
        model.setImages(mockImages);
        return model;
    }



    public Page<ProductModel> getUserFavoriteProductsList(Long currentUserId, int first, int max) {
        return this.userProductRepository.findAll(currentUserId, PageRequest.of(first, max));
    }

    public Long getFavoritesCount(Long userId) {
        return this.userProductRepository.getCheckoutCount(userId);
    }
}
