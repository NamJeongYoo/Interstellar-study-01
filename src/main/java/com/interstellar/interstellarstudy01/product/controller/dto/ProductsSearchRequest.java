package com.interstellar.interstellarstudy01.product.controller.dto;

import jakarta.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record ProductsSearchRequest(
        @Min(0) Integer page,
        @Min(1) Integer size
) {
    public Pageable toPageable() {
        int p = (page == null || page < 0) ? 0 : page;
        int s = (size == null || size < 1) ? 10 : size;

        return PageRequest.of(p, s, Sort.by("productId").descending());
    }
}
