package com.interstellar.interstellarstudy01.investment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InvestmentResult {
    Long userId;
    Long productId;
    Long investingAmount;
    LocalDateTime investingDate;
}
