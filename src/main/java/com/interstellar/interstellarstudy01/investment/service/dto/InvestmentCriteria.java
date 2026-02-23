package com.interstellar.interstellarstudy01.investment.service.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestmentCriteria {
    Long userId;
    Long productId;
    Long investingAmount;
}
