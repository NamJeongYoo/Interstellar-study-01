package com.interstellar.interstellarstudy01.product.repository;

import com.interstellar.interstellarstudy01.product.domain.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // 비관적 락 설정
    @Query("select p from ProductEntity p where p.productId = :id")
    Optional<ProductEntity> findByIdWithLock(@Param("id") Long id);
}
