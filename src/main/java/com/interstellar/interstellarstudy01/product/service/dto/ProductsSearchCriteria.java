package com.interstellar.interstellarstudy01.product.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@Builder
public class ProductsSearchCriteria {
    Pageable pageable;
}
