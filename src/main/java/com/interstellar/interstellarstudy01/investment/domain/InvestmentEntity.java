package com.interstellar.interstellarstudy01.investment.domain;

import com.interstellar.interstellarstudy01.product.domain.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "investment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InvestmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investmentId;

    @Column(nullable = false)
    private Long userId; // X-USER-ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(nullable = false)
    private Long investingAmount;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime investedAt = LocalDateTime.now();
}