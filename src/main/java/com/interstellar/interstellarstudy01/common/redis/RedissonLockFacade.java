package com.interstellar.interstellarstudy01.common.redis;

import com.interstellar.interstellarstudy01.investment.service.InvestmentService;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentCriteria;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentResult;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockFacade {

    private final RedissonClient redissonClient;
    private final InvestmentService investmentService;

    public InvestmentResult doInvestmentWithLock(InvestmentCriteria criteria) {
        // 상품 ID를 기반으로 고유한 락 키 생성
        RLock lock = redissonClient.getLock("PRODUCT_LOCK:" + criteria.getProductId());
        InvestmentResult result;
        try {
            // waitTime: 락을 얻기 위해 기다리는 시간 (5초)
            // leaseTime: 락을 획득 후 자동으로 해제되는 시간 (3초)
            boolean available = lock.tryLock(5, 3, TimeUnit.SECONDS);

            if (!available) {
                throw new RuntimeException("락을 획득하지 못했습니다. 잠시 후 다시 시도해주세요.");
            }

            // 실제 비즈니스 로직 수행
            result = investmentService.doInvestment(criteria);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 반드시 락을 해제해야 함 (현재 스레드가 점유 중일 때만)
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

        return result;
    }
}