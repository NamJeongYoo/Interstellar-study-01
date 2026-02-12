package com.interstellar.interstellarstudy01.investment.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InvestmentRequest(
        @NotNull
        Long productId,

        @NotNull @Min(1)
        Long investingAmount
) {

}