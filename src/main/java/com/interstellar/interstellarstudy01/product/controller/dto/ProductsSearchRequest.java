package com.interstellar.interstellarstudy01.product.controller.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record ProductsSearchRequest(
        Integer page,
        Integer size
) {
    public Pageable toPageable() {
        int p = (page == null || page < 0) ? 0 : page;
        int s = (size == null || size < 1) ? 10 : size;

        return PageRequest.of(p, s, Sort.by("productId").descending());
    }
}
