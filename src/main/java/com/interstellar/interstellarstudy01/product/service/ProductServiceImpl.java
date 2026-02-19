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
        // 1. Pageable 생성
        Pageable pageable = criteria.getPageable();

        // 2. DB에서 페이징 쿼리 실행 (Limit, Offset 쿼리가 자동으로 나감)
        Page<ProductEntity> productPage = productRepository.findAll(pageable);

        // 3. 엔티티 -> DTO 변환
        return ProductsSearchResult.builder()
                .totalCount((int) productPage.getTotalElements())
                .productList(productPage.getContent().stream()
                        .map(productMapper::toProduct)
                        .toList())
                .build();
    }
}
