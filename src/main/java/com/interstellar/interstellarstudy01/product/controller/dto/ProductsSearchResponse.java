package com.interstellar.interstellarstudy01.product.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductsSearchResponse(
        int totalCount,
        List<Product> productList
) {
    public record Product(
            long productId,
            String title,
            LocalDateTime startedAt,
            LocalDateTime finishedAt,
            int investCount,
            String status,
            BigDecimal currentInvestingAmount,
            BigDecimal totalInvestingAmount
    ) {

    }
}
