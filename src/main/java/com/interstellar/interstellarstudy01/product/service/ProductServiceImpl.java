package com.interstellar.interstellarstudy01.product.service;

import com.interstellar.interstellarstudy01.product.repository.ProductRepository;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchCriteria;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public ProductsSearchResult getProducts(ProductsSearchCriteria criteria) {
        // Todo
        return null;
    }
}
