package com.interstellar.interstellarstudy01.investment.service;

import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentCriteria;
import com.interstellar.interstellarstudy01.investment.service.dto.InvestmentResult;

public interface InvestmentService {
    InvestmentResult doInvestment(InvestmentCriteria criteria);
}
