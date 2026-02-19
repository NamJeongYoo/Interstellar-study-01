package com.interstellar.interstellarstudy01.investment.controller;

import com.interstellar.interstellarstudy01.common.redis.RedissonLockFacade;
import com.interstellar.interstellarstudy01.investment.controller.dto.InvestmentRequest;
import com.interstellar.interstellarstudy01.investment.controller.dto.InvestmentResponse;
import com.interstellar.interstellarstudy01.investment.controller.dto.MyInvestmentResponse;
import com.interstellar.interstellarstudy01.investment.mapper.InvestmentMapper;
import com.interstellar.interstellarstudy01.investment.service.InvestmentService;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentCriteria;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentResult;
import com.interstellar.interstellarstudy01.investment.service.dto.MyInvestmentResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/invests")
public class InvestmentController {

    private final InvestmentMapper investmentMapper;
    private final RedissonLockFacade redissonLockFacade;
    private final InvestmentService investmentService;

    @PostMapping
    public ResponseEntity<InvestmentResponse> doInvest
            (@RequestHeader("X-USER-ID") Long userId,
             @RequestBody @Valid InvestmentRequest request) {
        InvestmentCriteria criteria = investmentMapper.toInvestmentCriteria(userId, request);
        InvestmentResult result = redissonLockFacade.doInvestmentWithLock(criteria);
        return ResponseEntity.status(HttpStatus.CREATED).body(investmentMapper.toInvestmentResponse(result));
    }

    @GetMapping("/my")
    public ResponseEntity<MyInvestmentResponse> getMyInvestments(
            @RequestHeader("X-USER-ID") Long userId) {
        MyInvestmentResult result = investmentService.getMyInvestments(userId);
        return ResponseEntity.ok(investmentMapper.toMyInvestmentResponse(result));
    }
}