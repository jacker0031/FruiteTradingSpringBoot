package com.example.fruitetrading.services;

import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruitePurchaseDetailsNotFound;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;

import java.util.List;

public interface FruitePurchaseService {
    public FruitePurchase acceptFruitePurchaseDetails(FruitePurchase fruitePurchase) throws FruiteVendorNotFoundException;
    public FruitePurchase getFruitePurchaseDetails(int id) throws FruitePurchaseDetailsNotFound;
    public boolean deleteById(int id) throws FruitePurchaseDetailsNotFound;
    public List<FruitePurchase> getAllFruitePurchase();
    public  List<FruitePurchase> getAllFruitePurchaseByFruiteVendorAndFruiteName(FruiteVendor fruiteVendor,String fruiteName);
    public void updateVendorProfit(int newProfit,int vendorId);
    public void updateFruitePurchase(int newQuantity,int purchaseId);
}
