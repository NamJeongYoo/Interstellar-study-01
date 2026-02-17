package com.interstellar.interstellarstudy01.product.domain;

import com.interstellar.interstellarstudy01.product.constant.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long totalInvestingAmount;

    @Builder.Default
    @Column(nullable = false)
    private Long currentInvestingAmount = 0L;

    @Builder.Default
    @Column(nullable = false)
    private Integer investCount = 0;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    @Column(nullable = false)
    private LocalDateTime finishedAt;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.OPEN;

    public void updateInvestingInfo(Long investingAmount) {
        // 1. 현재 모집 금액 가산
        this.currentInvestingAmount += investingAmount;

        // 2. 투자자 수 가산 (기존 investCount 필드가 Integer이므로 +1)
        this.investCount += 1;

        // 3. 비즈니스 로직: 총 모집 금액에 도달했는지 확인
        checkSoldOut();
    }

    private void checkSoldOut() {
        // 현재 금액이 총 모집 금액과 같거나 (혹시 모를 상황 대비) 크다면 상태 변경
        if (this.currentInvestingAmount >= this.totalInvestingAmount) {
            this.status = ProductStatus.SOLD_OUT; // 또는 CLOSED 등 정의하신 상태값
        }
    }

    public void markAsSoldOut() {
        this.status = ProductStatus.SOLD_OUT;
        // 필요한 경우 마감 시간 기록 등 추가 로직 수행
        // this.finishedAt = LocalDateTime.now();
    }

}