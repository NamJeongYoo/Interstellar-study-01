package com.interstellar.interstellarstudy01.product.controller;

import com.interstellar.interstellarstudy01.product.constant.ProductStatus;
import com.interstellar.interstellarstudy01.product.controller.dto.ProductsSearchRequest;
import com.interstellar.interstellarstudy01.product.controller.dto.ProductsSearchResponse;
import com.interstellar.interstellarstudy01.product.mapper.ProductMapper;
import com.interstellar.interstellarstudy01.product.service.ProductService;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchCriteria;
import com.interstellar.interstellarstudy01.product.service.dto.ProductsSearchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class) // 특정 컨트롤러만 테스트 대상에 포함
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Test
    @DisplayName("상품 검색 API - 성공 케이스")
    void getProducts_Success() throws Exception {
        ProductsSearchResult mockResult = ProductsSearchResult.builder()
                .totalCount(2)
                .productList(List.of(
                        ProductsSearchResult.Product.builder()
                                .productId(2L)
                                .title("부동산포트폴리오")
                                .startedAt(LocalDateTime.parse("2026-02-12T00:00:00"))
                                .finishedAt(LocalDateTime.parse("2026-12-31T23:59:59"))
                                .investCount(1)
                                .status(ProductStatus.OPEN)
                                .currentInvestingAmount(10000L)
                                .totalInvestingAmount(5000000L).build(),
                        ProductsSearchResult.Product.builder()
                                .productId(1L)
                                .title("개인신용포트폴리오")
                                .startedAt(LocalDateTime.parse("2026-02-12T00:00:00"))
                                .finishedAt(LocalDateTime.parse("2026-12-18T23:59:59"))
                                .investCount(1)
                                .status(ProductStatus.SOLD_OUT)
                                .currentInvestingAmount(1000000L)
                                .totalInvestingAmount(1000000L).build()))
                .build();
        ProductsSearchResponse mockResponse = ProductsSearchResponse.builder()
                .totalCount(2)
                .productList(List.of(
                        ProductsSearchResponse.Product.builder()
                                .productId(2L)
                                .title("부동산포트폴리오")
                                .startedAt(LocalDateTime.parse("2026-02-12T00:00:00"))
                                .finishedAt(LocalDateTime.parse("2026-12-31T23:59:59"))
                                .investCount(1)
                                .status("OPEN")
                                .currentInvestingAmount(10000L)
                                .totalInvestingAmount(5000000L).build(),
                        ProductsSearchResponse.Product.builder()
                                .productId(1L)
                                .title("개인신용포트폴리오")
                                .startedAt(LocalDateTime.parse("2026-02-12T00:00:00"))
                                .finishedAt(LocalDateTime.parse("2026-12-18T23:59:59"))
                                .investCount(1)
                                .status("SOLD_OUT")
                                .currentInvestingAmount(1000000L)
                                .totalInvestingAmount(1000000L).build()))
                .build();

        given(productMapper.toProductsSearchCriteria(any(ProductsSearchRequest.class)))
                .willReturn(ProductsSearchCriteria.builder()
                        .pageable(Pageable.ofSize(10))
                        .build());

        given(productService.getProducts(any(ProductsSearchCriteria.class)))
                .willReturn(mockResult);

        given(productMapper.toProductsSearchResponse(any(ProductsSearchResult.class)))
                .willReturn(mockResponse);

        // when & then: API 호출 및 검증
        mockMvc.perform(get("/api/v1/products") // 실제 경로에 맞게 수정
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("상품 검색 API - 잘못된 파라미터(size=0) 전달 시 400 에러")
    void getProducts_Fail_Invalid_Size() throws Exception {
        mockMvc.perform(get("/api/v1/products")
                        .param("page", "0")
                        .param("size", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}