package com.interstellar.interstellarstudy01.product.service;

import com.interstellar.interstellarstudy01.product.domain.ProductEntity;
import com.interstellar.interstellarstudy01.product.mapper.ProductMapper;
import com.interstellar.interstellarstudy01.product.repository.ProductRepository;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchCriteria;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    @Override
    public ProductsSearchResult getProducts(ProductsSearchCriteria criteria) {
        List<ProductEntity> productList = productRepository.findAll();
        return ProductsSearchResult.builder()
                .totalCount(productList.size())
                .productList(productList.stream()
                        .map(productMapper::toProduct)
                        .toList())
                .build();
    }
}
