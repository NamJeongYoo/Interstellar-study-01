package com.interstellar.interstellarstudy01.product.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductsSearchResponse(
        int totalCount,
        List<Product> productList
) {
    @Builder
    public record Product(
            Long productId,
            String title,
            LocalDateTime startedAt,
            LocalDateTime finishedAt,
            Integer investCount,
            String status,
            Long currentInvestingAmount,
            Long totalInvestingAmount
    ) {

    }
}
