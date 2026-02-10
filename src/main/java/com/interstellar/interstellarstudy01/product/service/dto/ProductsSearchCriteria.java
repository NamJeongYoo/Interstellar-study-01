package com.interstellar.interstellarstudy01.product.service.dto;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class ProductsSearchCriteria {
    Pageable pageable;
}
