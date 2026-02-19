package com.interstellar.interstellarstudy01.investment.mapper;

import com.interstellar.interstellarstudy01.investment.controller.dto.InvestmentRequest;
import com.interstellar.interstellarstudy01.investment.controller.dto.InvestmentResponse;
import com.interstellar.interstellarstudy01.investment.controller.dto.MyInvestmentResponse;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentCriteria;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentResult;
import com.interstellar.interstellarstudy01.investment.service.dto.MyInvestmentResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {
    InvestmentCriteria toInvestmentCriteria(Long userId, InvestmentRequest request);

    InvestmentResponse toInvestmentResponse(InvestmentResult result);

    MyInvestmentResponse toMyInvestmentResponse(MyInvestmentResult result);
}
