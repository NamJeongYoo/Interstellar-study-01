package com.interstellar.interstellarstudy01.product.repository;

import com.interstellar.interstellarstudy01.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
