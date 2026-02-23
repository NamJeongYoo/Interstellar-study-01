package com.interstellar.interstellarstudy01.product.service.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsSearchCriteria {
    Pageable pageable;
}
