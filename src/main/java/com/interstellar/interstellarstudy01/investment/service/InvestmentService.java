package com.interstellar.interstellarstudy01.investment.service;

import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentCriteria;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentResult;
import com.interstellar.interstellarstudy01.investment.service.dto.MyInvestmentResult;

public interface InvestmentService {
    InvestmentResult doInvestment(InvestmentCriteria criteria);
    MyInvestmentResult getMyInvestments(Long userId);
}
