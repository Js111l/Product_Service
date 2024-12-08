package com.ecom.productservice.service;

import com.ecom.productservice.controller.MainPageBannerModel;
import com.ecom.productservice.criteria.ProductSearchCriteria;
import com.ecom.productservice.dao.entities.Product;
import com.ecom.productservice.dao.entities.UserProduct;
import com.ecom.productservice.dao.mapper.ProductMapper;
import com.ecom.productservice.dao.repository.MainPageBannerRepository;
import com.ecom.productservice.dao.repository.ProductCategoryRepository;
import com.ecom.productservice.dao.repository.ProductRepository;
import com.ecom.productservice.dao.repository.UserProductRepository;
import com.ecom.productservice.exception.ApiExceptionType;
import com.ecom.productservice.exception.ApplicationRuntimeException;
import com.ecom.productservice.model.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ProductService implements BaseService<Product, Long> {

    private final ProductRepository productRepository;
    private final UserProductRepository userProductRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final MainPageBannerRepository mainPageBannerRepository;

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product getById(Long id) {
        return this.productRepository
                .findById(id)
                .orElseThrow(() -> new ApplicationRuntimeException(ApiExceptionType.NOT_FOUND));
    }

    @Override
    public Product deleteById(Long id) {
        return getById(id);
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
        return ProductMapper.INSTANCE.mapEntityToDetailModel(result);
    }

    public Long getFavoritesCount(Long userId) {
        return this.userProductRepository.getCheckoutCount(userId);
    }

    public List<ProductCategoryModel> getParentCategories() {
        return this.productCategoryRepository
                .findParentCategories()
                .stream()
                .map(ProductMapper.INSTANCE::mapEntityToProductCategoryModel)
                .toList();
    }

    public Page<ProductModel> getList(ProductSearchCriteria sc) {
        var page = this.productRepository.findAll(sc.getSpecification(), sc.getPageRequest());
        return new PageImpl<>(page.map(ProductMapper.INSTANCE::mapEntityToDetailModel).toList(), sc.getPageRequest(), page.getTotalElements());
    }

    public List<ProductCategoryModel> getChildrenCategories(Long parentId, String parentPrefix, Boolean firstLevel) {
        if (firstLevel) {
            return this.productCategoryRepository.findFirstLevelChildren(parentId, parentPrefix)
                    .stream()
                    .map(ProductMapper.INSTANCE::mapEntityToProductCategoryModel)
                    .toList();
        } else {
            return this.productCategoryRepository.findAllChildren(parentId, parentPrefix)
                    .stream()
                    .map(ProductMapper.INSTANCE::mapEntityToProductCategoryModel)
                    .toList();
        }
    }

    public List<ProductCategoryModel> getChildrenCategories(String parentPrefix, Boolean firstLevel) {
        if (firstLevel) {
            return this.productCategoryRepository.findFirstLevelChildren(parentPrefix)
                    .stream()
                    .map(ProductMapper.INSTANCE::mapEntityToProductCategoryModel)
                    .toList();
        } else {
            return this.productCategoryRepository.findAllChildren(parentPrefix)
                    .stream()
                    .map(ProductMapper.INSTANCE::mapEntityToProductCategoryModel)
                    .toList();
        }
    }

    public List<ProductCategoryMenuBarModel> getMenuBarCategories() {
        final var result = new ArrayList<ProductCategoryMenuBarModel>();
        this.productCategoryRepository.findParentCategories().forEach(parent -> {
            var model = new ProductCategoryMenuBarModel();
            model.setId(parent.getId());
            model.setPath(parent.getPath());
            model.setLabel(parent.getLabel());
            model.setFirstLevelChildren(this.getChildrenCategories(parent.getId(), parent.getPath(), true));
            result.add(model);
        });
        return result;
    }

    public List<ProductCategoryOptionModel> getParentCategoriesOptionModels() {
        return this.productCategoryRepository.findParentCategories()
                .stream().map(ProductMapper.INSTANCE::mapEntityToOptionModel)
                .toList();
    }

    public Page<ReturnListModel> getReturns(ProductSearchCriteria sc) {
        this.productRepository.findAll(sc.getSpecification(), sc.getPageRequest())
                .toList();
        return null;
    }

    public void addUserProduct(UserProductRequestModel requestModel, String sessionId) {
        var entity = new UserProduct();
        entity.setProductId(requestModel.productId());
        entity.setSessionId(sessionId);
        this.userProductRepository.save(entity);
    }

    public List<MainPageBannerModel> getBannerData() {
        return this.mainPageBannerRepository.findAllActive()
                .stream().map(x -> new MainPageBannerModel(x.getId(), x.getActive(), x.getImageUrl()))
                .toList();
    }
}
