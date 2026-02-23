package com.interstellar.interstellarstudy01.investment.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MyInvestmentResponse(
        int totalCount,
        List<Investment> investmentList
) {
    @Builder
    public record Investment(
            Long productId,
            String title,
            Long totalInvestingAmount,
            Long myInvestingAmount,
            LocalDateTime investingDate
    ) {

    }
}
