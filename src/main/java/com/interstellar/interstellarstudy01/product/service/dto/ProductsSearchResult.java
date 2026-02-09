package com.interstellar.interstellarstudy01.product.service.dto;

import com.interstellar.interstellarstudy01.product.constant.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductsSearchResult {
    Integer totalCount;
    List<Product> productList;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Product {
        Long productId;
        String title;
        LocalDateTime startedAt;
        LocalDateTime finishedAt;
        Integer investCount;
        ProductStatus status;
        BigDecimal currentInvestingAmount;
        BigDecimal totalInvestingAmount;
    }
}
