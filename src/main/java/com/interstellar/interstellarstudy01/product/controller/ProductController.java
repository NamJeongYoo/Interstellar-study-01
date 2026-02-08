package com.interstellar.interstellarstudy01.product.controller;

import com.interstellar.interstellarstudy01.product.controller.dto.ProductsSearchRequest;
import com.interstellar.interstellarstudy01.product.controller.dto.ProductsSearchResponse;
import com.interstellar.interstellarstudy01.product.mapper.ProductMapper;
import com.interstellar.interstellarstudy01.product.service.ProductService;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchCriteria;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductsSearchResponse> getProducts(ProductsSearchRequest request) {
        ProductsSearchCriteria criteria = productMapper.toProductsSearchCriteria(request);
        ProductsSearchResult result = productService.getProducts(criteria);
        return ResponseEntity.ok(productMapper.toProductsSearchResponse(result));
    }
}
