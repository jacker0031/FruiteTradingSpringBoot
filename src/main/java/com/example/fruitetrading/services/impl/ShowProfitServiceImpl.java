package com.example.fruitetrading.services.impl;

import com.example.fruitetrading.dao.FruiteVendorDao;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.services.ShowProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowProfitServiceImpl implements ShowProfitService {
    @Autowired
    FruiteVendorDao fruiteVendorDao;
    @Override
    public FruiteVendor showProfit(FruiteVendor fruiteVendor) {
        return fruiteVendorDao.getById(fruiteVendor.getVendorId());
    }
}
