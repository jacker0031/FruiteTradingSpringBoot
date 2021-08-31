package com.example.fruitetrading.dao;

import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FruitePurchaseDao extends JpaRepository<FruitePurchase,Integer> {

    public List<FruitePurchase> findAllByFruiteVendorAndFruiteName(FruiteVendor fruiteVendor,String fruiteName);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update FruitePurchase f set f.quantity=:newQuantity where f.purchaseId=:purchaseId")
    void updateFruiteQuantity(@Param("newQuantity") int newQuantity, @Param("purchaseId") int purchaseId);
}
