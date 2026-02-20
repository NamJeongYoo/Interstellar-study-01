package com.interstellar.interstellarstudy01.investment.repository;

import com.interstellar.interstellarstudy01.investment.domain.InvestmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<InvestmentEntity, Long> {
    @Query("SELECT i FROM InvestmentEntity i WHERE i.userId = :userId")
    List<InvestmentEntity> findAllByUserIdWithProduct(@Param("userId") Long userId);
}