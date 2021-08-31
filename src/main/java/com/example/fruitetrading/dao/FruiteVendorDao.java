package com.example.fruitetrading.dao;

import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface FruiteVendorDao extends JpaRepository<FruiteVendor,Integer> {
    Optional<FruiteVendor> findByVendorName(String fruiteVendorName);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update FruiteVendor f set f.profit=:newProfit where f.vendorId=:vendorId")
    void updateVendoreProfit(@Param("newProfit") int Profit,@Param("vendorId") int vendorId);

}
