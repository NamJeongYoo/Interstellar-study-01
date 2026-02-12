package com.interstellar.interstellarstudy01.product.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductsSearchResponse(
        int totalCount,
        List<Product> productList
) {
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
