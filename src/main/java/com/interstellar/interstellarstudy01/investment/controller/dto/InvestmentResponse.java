package com.interstellar.interstellarstudy01.investment.controller.dto;

import java.time.LocalDateTime;

public record InvestmentResponse(
        Long userId,
        Long productId,
        Long investingAmount,
        LocalDateTime investingDate
) {
}
