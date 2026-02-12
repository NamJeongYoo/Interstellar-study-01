package com.interstellar.interstellarstudy01.product.service.dto;

import com.interstellar.interstellarstudy01.product.constant.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductsSearchResult {
    Integer totalCount;
    List<Product> productList;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Product {
        Long productId;
        String title;
        LocalDateTime startedAt;
        LocalDateTime finishedAt;
        Integer investCount;
        ProductStatus status;
        Long currentInvestingAmount;
        Long totalInvestingAmount;
    }
}
