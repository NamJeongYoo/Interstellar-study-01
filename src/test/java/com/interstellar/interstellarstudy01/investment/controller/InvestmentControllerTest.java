package com.interstellar.interstellarstudy01.investment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interstellar.interstellarstudy01.common.redis.RedissonLockFacade;
import com.interstellar.interstellarstudy01.investment.controller.dto.InvestmentRequest;
import com.interstellar.interstellarstudy01.investment.controller.dto.InvestmentResponse;
import com.interstellar.interstellarstudy01.investment.controller.dto.MyInvestmentResponse;
import com.interstellar.interstellarstudy01.investment.mapper.InvestmentMapper;
import com.interstellar.interstellarstudy01.investment.service.InvestmentService;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentCriteria;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentResult;
import com.interstellar.interstellarstudy01.investment.service.dto.MyInvestmentResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvestmentController.class) // Controller 계층만 슬라이스 테스트
class InvestmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // 객체를 JSON 문자열로 변환

    @MockBean
    private InvestmentService investmentService; // 서비스 계층 모킹

    @MockBean
    private InvestmentMapper investmentMapper;

    @MockBean
    private RedissonLockFacade redissonLockFacade;

    @Test
    @DisplayName("투자 실행 API가 성공하면 201 OK와 투자 정보를 반환한다")
    void doInvest_Success() throws Exception {
        Long userId = 20160828L;
        LocalDateTime investTime = LocalDateTime.parse("2026-02-12T00:00:00");

        InvestmentRequest mockRequest = InvestmentRequest.builder()
                .productId(1L)
                .investingAmount(500000L)
                .build();

        InvestmentCriteria mockCriteria = InvestmentCriteria.builder()
                .userId(userId)
                .productId(1L)
                .investingAmount(500000L)
                .build();

        InvestmentResult mockResult = InvestmentResult.builder()
                .userId(userId)
                .productId(1L)
                .investingAmount(500000L)
                .investingDate(investTime)
                .build();

        InvestmentResponse mockResponse = InvestmentResponse.builder()
                .userId(userId)
                .productId(1L)
                .investingAmount(500000L)
                .investingDate(investTime)
                .build();

        given(investmentMapper.toInvestmentCriteria(eq(userId), any(InvestmentRequest.class)))
                .willReturn(mockCriteria);

        given(redissonLockFacade.doInvestmentWithLock(any(InvestmentCriteria.class)))
                .willReturn(mockResult);

        given(investmentMapper.toInvestmentResponse(any(InvestmentResult.class)))
                .willReturn(mockResponse);

        mockMvc.perform(post("/api/v1/investments")
                        .header("X-USER-ID", userId)
                        .contentType(MediaType.APPLICATION_JSON) // 또는 APPLICATION_JSON
                        .content(objectMapper.writeValueAsString(mockRequest))) // 객체를 JSON으로 변환
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.investingAmount").value(500000L))
                .andExpect(jsonPath("$.investingDate").value("2026-02-12T00:00:00"));
    }

    @Test
    @DisplayName("사용자의 투자 목록을 조회하면 200OK와 리스트를 반환한다")
    void getMyInvestments_Success() throws Exception {
        Long userId = 20160828L;
        LocalDateTime investTime = LocalDateTime.parse("2026-02-23T19:40:00");

        MyInvestmentResponse mockResponse = new MyInvestmentResponse(2,
                List.of(
                        new MyInvestmentResponse.Investment(1L, "개인신용포트폴리오"
                                , 1000000L, 5000L, investTime),
                        new MyInvestmentResponse.Investment(2L, "부동산포트폴리오"
                                , 5000000L, 10000L, investTime)
                )
        );

        MyInvestmentResult mockResult = MyInvestmentResult.builder()
                .totalCount(2)
                .investmentList(List.of(
                        MyInvestmentResult.Investment.builder()
                                .productId(1L)
                                .title("개인신용포트폴리오")
                                .totalInvestingAmount(1000000L)
                                .myInvestingAmount(5000L)
                                .investingDate(investTime)
                                .build(),
                        MyInvestmentResult.Investment.builder()
                                .productId(2L)
                                .title("부동산포트폴리오")
                                .totalInvestingAmount(5000000L)
                                .myInvestingAmount(10000L)
                                .investingDate(investTime)
                                .build()))
                .build();

        // 서비스 호출 시 위 리스트를 반환하도록 모킹
        given(investmentService.getMyInvestments(eq(userId)))
                .willReturn(mockResult);

        given(investmentMapper.toMyInvestmentResponse(any(MyInvestmentResult.class)))
                .willReturn(mockResponse);

        // 2. When & Then
        mockMvc.perform(get("/api/v1/investments/my")
                        .header("X-USER-ID", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.investmentList[0].productId").value(1L))
                .andExpect(jsonPath("$.investmentList[0].title").value("개인신용포트폴리오"))
                .andExpect(jsonPath("$.investmentList[0].totalInvestingAmount").value(1000000L))
                .andExpect(jsonPath("$.investmentList[0].myInvestingAmount").value(5000L))
                .andExpect(jsonPath("$.investmentList[0].investingDate").value("2026-02-23T19:40:00"))
                .andExpect(jsonPath("$.investmentList[1].productId").value(2L))
                .andExpect(jsonPath("$.investmentList[1].title").value("부동산포트폴리오"))
                .andExpect(jsonPath("$.investmentList[1].totalInvestingAmount").value(5000000L))
                .andExpect(jsonPath("$.investmentList[1].myInvestingAmount").value(10000L))
                .andExpect(jsonPath("$.investmentList[1].investingDate").value("2026-02-23T19:40:00"));
    }
}