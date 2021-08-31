package com.example.fruitetrading.services.impl;

import com.example.fruitetrading.dao.FruiteStockDao;
import com.example.fruitetrading.dao.FruiteVendorDao;
import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteStock;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruitePurchaseDetailsNotFound;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;
import com.example.fruitetrading.exception.StockForRequestedFruiteisNotPresentException;

import com.example.fruitetrading.services.FruitePurchaseService;
import com.example.fruitetrading.services.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class SellServiceImpl implements SellService {
    @Autowired
    FruitePurchaseService fruitePurchaseService;
    @Autowired
    FruiteStockDao fruiteStockDao;
    @Autowired
    FruiteVendorDao fruiteVendorDao;
    public boolean sellFruite(FruiteVendor fruiteVendor, String fruiteName, int quantity, int price) throws StockForRequestedFruiteisNotPresentException, FruitePurchaseDetailsNotFound, FruiteVendorNotFoundException {
        FruiteVendor fruiteVendor1=fruiteVendorDao.findById(fruiteVendor.getVendorId()).orElseThrow(()->new FruiteVendorNotFoundException("FruiteVendor Not Found With id"+fruiteVendor.getVendorId()));
        FruiteStock fruiteStock=fruiteStockDao.findByVendorIdAndFruiteName(fruiteVendor.getVendorId(),fruiteName).orElseThrow(()->new StockForRequestedFruiteisNotPresentException("The Stock for Requested Fruite is Not Availble with id:- "+fruiteVendor.getVendorId()));
        int sellQuantity=quantity;
        int cost=0;
        List<FruitePurchase> fruitePurchaseList=fruitePurchaseService.getAllFruitePurchaseByFruiteVendorAndFruiteName(fruiteVendor,fruiteName);
        fruitePurchaseList.sort(Comparator.comparing(FruitePurchase::getPurchaseId));

        if(fruiteStock.getTotalQuantity()<quantity)
        {
            throw new StockForRequestedFruiteisNotPresentException("The Stock for Requested Fruite is Not Availble with id:- "+fruiteVendor.getVendorId());
        }
        for (FruitePurchase fruitePurchase : fruitePurchaseList) {
            Optional<FruiteStock> fruiteStock1 = fruiteStockDao.findByVendorIdAndFruiteName(fruiteVendor.getVendorId(), fruiteName);
            if (fruitePurchase.getQuantity() > sellQuantity) {
                int remaining = fruitePurchase.getQuantity() - sellQuantity;
                cost += sellQuantity * fruitePurchase.getPrice();
                fruitePurchaseService.updateFruitePurchase(remaining, fruitePurchase.getPurchaseId());
                fruiteStockDao.updateFruiteQuantity(fruiteStock1.get().getTotalQuantity() - sellQuantity, fruiteStock.getStockId());
                break;
            }
            sellQuantity -= fruitePurchase.getQuantity();
            cost += fruitePurchase.getQuantity() * fruitePurchase.getPrice();
            fruitePurchaseService.deleteById(fruitePurchase.getPurchaseId());
        }
        fruiteVendorDao.updateVendoreProfit(fruiteVendor1.getProfit()+((quantity*price)-cost),fruiteVendor1.getVendorId());
        return true;



    }


}
