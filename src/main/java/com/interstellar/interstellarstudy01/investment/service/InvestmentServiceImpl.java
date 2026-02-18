package com.interstellar.interstellarstudy01.investment.service;

import com.interstellar.interstellarstudy01.investment.domain.InvestmentEntity;
import com.interstellar.interstellarstudy01.investment.repository.InvestmentRepository;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentCriteria;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentResult;
import com.interstellar.interstellarstudy01.product.constant.ProductStatus;
import com.interstellar.interstellarstudy01.product.domain.ProductEntity;
import com.interstellar.interstellarstudy01.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final ProductRepository productRepository;
    private final InvestmentRepository investmentRepository;

    @Override
    @Transactional
    public InvestmentResult doInvestment(InvestmentCriteria criteria) {
        ProductEntity product = productRepository.findByIdWithLock(criteria.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        // 2. 투자 가능 여부 검증
        if (product.getStatus() == ProductStatus.SOLD_OUT) {
            throw new RuntimeException("이미 모집이 완료된 상품입니다.");
        }

        long expectedAmount = product.getCurrentInvestingAmount() + criteria.getInvestingAmount();

        if (expectedAmount > product.getTotalInvestingAmount()) {
            // 총 투자 모집 금액을 초과할 수 없습니다.
            throw new RuntimeException("총 투자 모집 금액을 초과할 수 없습니다.");
        }

        // 3. 투자 내역 저장
        InvestmentEntity investment = InvestmentEntity.builder()
                .userId(criteria.getUserId())
                .product(product)
                .investingAmount(criteria.getInvestingAmount())
                .investedAt(LocalDateTime.now())
                .build();
        investmentRepository.save(investment);

        // 4. 상품 정보 업데이트 (Dirty Checking 활용)
        product.updateInvestingInfo(criteria.getInvestingAmount());

        // 5. 완판 처리 체크
        if (product.getCurrentInvestingAmount().equals(product.getTotalInvestingAmount())) {
            product.markAsSoldOut();
        }

        return InvestmentResult.builder()
                .userId(investment.getUserId())
                .productId(investment.getProduct().getProductId())
                .investingAmount(investment.getInvestingAmount())
                .investingDate(investment.getInvestedAt())
                .build();
    }
}
