package com.interstellar.interstellarstudy01.product.service;

import com.interstellar.interstellarstudy01.product.domain.ProductEntity;
import com.interstellar.interstellarstudy01.product.mapper.ProductMapper;
import com.interstellar.interstellarstudy01.product.repository.ProductRepository;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchCriteria;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public ProductsSearchResult getProducts(ProductsSearchCriteria criteria) {
        Pageable pageable = criteria.getPageable();
        Page<ProductEntity> productPage = productRepository.findAll(pageable);
        return ProductsSearchResult.builder()
                .totalCount((int) productPage.getTotalElements())
                .productList(productPage.getContent().stream()
                        .map(productMapper::toProduct)
                        .toList())
                .build();
    }
}
