package com.interstellar.interstellarstudy01.investment.service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyInvestmentResult {
    int totalCount;
    List<Investment> investmentList;

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Investment {
        Long productId;
        String title;
        Long totalInvestingAmount;
        Long myInvestingAmount;
        LocalDateTime investingDate;
    }
}
