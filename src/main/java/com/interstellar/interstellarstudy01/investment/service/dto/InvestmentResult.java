package com.interstellar.interstellarstudy01.investment.service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestmentResult {
    Long userId;
    Long productId;
    Long investingAmount;
    LocalDateTime investingDate;
}
