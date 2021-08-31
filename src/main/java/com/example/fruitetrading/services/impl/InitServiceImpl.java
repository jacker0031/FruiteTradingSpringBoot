package com.example.fruitetrading.services.impl;

import com.example.fruitetrading.dao.FruiteVendorDao;
import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;
import com.example.fruitetrading.services.FruitePurchaseService;
import com.example.fruitetrading.services.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;

@Service
public class InitServiceImpl implements InitService {
    @Autowired
    FruiteVendorDao fruiteVendorDao;

    @Autowired
    FruitePurchaseService fruitePurchaseService;

    @Override
    public void init() throws FruiteVendorNotFoundException {
        FruiteVendor fruiteVendor=new FruiteVendor();
        fruiteVendor.setVendorName("Jenish");
        fruiteVendor.setProfit(0);
        fruiteVendorDao.save(fruiteVendor);
        FruiteVendor fruiteVendor1=new FruiteVendor();
        fruiteVendor1.setVendorName("Kiran");
        fruiteVendor1.setProfit(0);
        fruiteVendorDao.save(fruiteVendor1);
        List<String> FruiteName=Arrays.asList("APPLE","BANANA","APPLE","APPLE");
        List<Integer> Price=Arrays.asList(100,20,110,120);
        List<Integer> Quantity=Arrays.asList(50,150,110,120);
//        FruitePurchase fruitePurchase=new FruitePurchase();
//        fruitePurchase.setQuantity(10);
//        fruitePurchase.setFruiteName("APPLE");
//        fruitePurchase.setFruiteVendor(fruiteVendor);
//        fruitePurchase.setPrice(100);
//        fruitePurchaseService.acceptFruitePurchaseDetails(fruitePurchase);
        for(int i=0;i<4;i++)
        {
            FruitePurchase fruitePurchase=new FruitePurchase();
            fruitePurchase.setFruiteVendor(fruiteVendor);
            fruitePurchase.setQuantity(Quantity.get(i));
            fruitePurchase.setFruiteName(FruiteName.get(i));
            fruitePurchase.setPrice(Price.get(i));
            fruitePurchaseService.acceptFruitePurchaseDetails(fruitePurchase);
        }
    }
}
