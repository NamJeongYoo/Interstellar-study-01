package com.interstellar.interstellarstudy01.investment.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InvestmentResponse(
        Long userId,
        Long productId,
        Long investingAmount,
        LocalDateTime investingDate
) {
}
