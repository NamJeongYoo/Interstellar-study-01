package com.interstellar.interstellarstudy01.investment.repository;

import com.interstellar.interstellarstudy01.investment.domain.InvestmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<InvestmentEntity, Long> {

}
