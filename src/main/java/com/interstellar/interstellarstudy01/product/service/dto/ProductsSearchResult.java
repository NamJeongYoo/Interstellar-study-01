package com.interstellar.interstellarstudy01.product.service.dto;

import com.interstellar.interstellarstudy01.product.constant.ProductStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsSearchResult {
    Integer totalCount;
    List<Product> productList;

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
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
