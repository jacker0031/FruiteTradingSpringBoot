package com.example.fruitetrading.services;

import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruitePurchaseDetailsNotFound;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;
import com.example.fruitetrading.exception.StockForRequestedFruiteisNotPresentException;

import java.util.List;

public interface SellService {
    public boolean sellFruite(FruiteVendor fruiteVendor,String FruiteName,int quantity,int price) throws StockForRequestedFruiteisNotPresentException, FruitePurchaseDetailsNotFound, FruiteVendorNotFoundException;


}
