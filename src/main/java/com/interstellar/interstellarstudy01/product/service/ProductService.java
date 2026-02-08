package com.interstellar.interstellarstudy01.product.service;

import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchCriteria;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchResult;

public interface ProductService {

    ProductsSearchResult getProducts(ProductsSearchCriteria criteria);
}
