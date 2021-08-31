package com.example.fruitetrading.dao;

import com.example.fruitetrading.entities.FruiteStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FruiteStockDao extends JpaRepository<FruiteStock,Integer> {
    Optional<FruiteStock> findByVendorIdAndFruiteName(int vendorId,String fruiteName);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update FruiteStock f set f.totalQuantity=:newQuantity where f.stockId=:stockId")
    void updateFruiteQuantity(@Param("newQuantity") int newQuantity, @Param("stockId") int stockId);
}
