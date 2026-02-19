package com.interstellar.interstellarstudy01.investment.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MyInvestmentResult {
    int totalCount;
    List<Investment> investmentList;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Investment {
        Long productId;
        String title;
        Long totalInvestingAmount;
        Long myInvestingAmount;
        LocalDateTime investingDate;
    }
}
