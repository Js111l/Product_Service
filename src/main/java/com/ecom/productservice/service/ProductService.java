package com.ecom.productservice.service;

import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.dao.repository.ProductRepository;
import com.ecom.productservice.exception.ErrorKey;
import com.ecom.productservice.exception.LogicalException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
//TODO
public class ProductService implements BaseService<Product, Long> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
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
}
