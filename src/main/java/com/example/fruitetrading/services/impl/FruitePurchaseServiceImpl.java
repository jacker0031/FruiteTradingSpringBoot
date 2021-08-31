package com.example.fruitetrading.services.impl;

import com.example.fruitetrading.dao.FruitePurchaseDao;
import com.example.fruitetrading.dao.FruiteStockDao;
import com.example.fruitetrading.dao.FruiteVendorDao;
import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteStock;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruitePurchaseDetailsNotFound;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;
import com.example.fruitetrading.services.FruitePurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitePurchaseServiceImpl implements FruitePurchaseService {
    @Autowired
    FruiteVendorDao fruiteVendorDao;

    @Autowired
    FruitePurchaseDao fruitePurchaseDao;

    @Autowired
    FruiteStockDao fruiteStockDao;


    @Override
    public FruitePurchase acceptFruitePurchaseDetails(FruitePurchase fruitePurchase) throws FruiteVendorNotFoundException {

        fruiteVendorDao.findById(fruitePurchase.getFruiteVendor().getVendorId()).orElseThrow(()->new FruiteVendorNotFoundException("The FruiteVendor Not Found With Id:- "+fruitePurchase.getFruiteVendor().getVendorId()));
        Optional<FruiteStock> fruiteStock=fruiteStockDao.findByVendorIdAndFruiteName(fruitePurchase.getFruiteVendor().getVendorId(),fruitePurchase.getFruiteName());
        if(fruiteStock.isEmpty())
        {
            FruiteStock fruiteStock1=new FruiteStock();
            fruiteStock1.setFruiteName(fruitePurchase.getFruiteName());
            fruiteStock1.setTotalQuantity(fruitePurchase.getQuantity());
            fruiteStock1.setVendorId(fruitePurchase.getFruiteVendor().getVendorId());
            fruiteStockDao.save(fruiteStock1);
        }
        else
        {
            fruiteStockDao.updateFruiteQuantity(fruitePurchase.getQuantity()+fruiteStock.get().getTotalQuantity(),fruiteStock.get().getStockId());
        }
          return fruitePurchaseDao.save(fruitePurchase);
    }

    @Override
    public FruitePurchase getFruitePurchaseDetails(int id) throws FruitePurchaseDetailsNotFound {
        return fruitePurchaseDao.findById(id).orElseThrow(()->new FruitePurchaseDetailsNotFound("The FruitePurchaseDetail Not Found With id "+id));
    }

    @Override
    public boolean deleteById(int id) throws FruitePurchaseDetailsNotFound {
        FruitePurchase fruitePurchase=fruitePurchaseDao.findById(id).orElseThrow(()->new FruitePurchaseDetailsNotFound("FruitePurchase Detail is Not Available"));
        fruitePurchaseDao.deleteById(id);
        FruiteStock fruiteStock=fruiteStockDao.findByVendorIdAndFruiteName(fruitePurchase.getFruiteVendor().getVendorId(),fruitePurchase.getFruiteName()).orElseThrow(()->new FruitePurchaseDetailsNotFound("Not Availabale"));
        fruiteStockDao.updateFruiteQuantity(fruiteStock.getTotalQuantity()-fruitePurchase.getQuantity(),fruiteStock.getStockId());
        return true;
    }




    @Override
    public List<FruitePurchase> getAllFruitePurchase() {
        return fruitePurchaseDao.findAll();
    }

    @Override
    public List<FruitePurchase> getAllFruitePurchaseByFruiteVendorAndFruiteName(FruiteVendor fruiteVendor, String fruiteName) {
        return fruitePurchaseDao.findAllByFruiteVendorAndFruiteName(fruiteVendor,fruiteName);
    }

    @Override
    public void updateVendorProfit(int newProfit, int vendorId) {
       fruiteVendorDao.updateVendoreProfit(newProfit,vendorId);
    }

    @Override
    public void updateFruitePurchase(int newQuantity, int purchaseId) {
        fruitePurchaseDao.updateFruiteQuantity(newQuantity, purchaseId);
    }


}
