package com.interstellar.interstellarstudy01.product.mapper;

import com.interstellar.interstellarstudy01.product.controller.dto.ProductsSearchRequest;
import com.interstellar.interstellarstudy01.product.controller.dto.ProductsSearchResponse;
import com.interstellar.interstellarstudy01.product.domain.ProductEntity;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchCriteria;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "pageable", expression = "java(request.toPageable())")
    ProductsSearchCriteria toProductsSearchCriteria(ProductsSearchRequest request);

    ProductsSearchResponse toProductsSearchResponse(ProductsSearchResult result);

    ProductsSearchResult.Product toProduct(ProductEntity entity);
}
