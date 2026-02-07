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

}