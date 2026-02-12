package com.interstellar.interstellarstudy01.investment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class InvestmentCriteria {
    Long userId;
    Long productId;
    Long investingAmount;
}
