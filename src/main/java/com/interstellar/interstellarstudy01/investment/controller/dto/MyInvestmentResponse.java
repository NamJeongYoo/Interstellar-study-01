package com.interstellar.interstellarstudy01.investment.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MyInvestmentResponse(
        int totalCount,
        List<Investment> investmentList
) {
    public record Investment(
            Long productId,
            String title,
            Long totalInvestingAmount,
            Long myInvestingAmount,
            LocalDateTime investingDate
    ) {

    }
}
