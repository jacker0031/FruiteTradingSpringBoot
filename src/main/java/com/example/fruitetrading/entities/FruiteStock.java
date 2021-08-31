package com.example.fruitetrading.entities;

import javax.persistence.*;

@Entity
public class FruiteStock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int stockId;

    public FruiteStock(int vendorId, String fruiteName, int totalQuantity) {
        this.vendorId = vendorId;
        this.fruiteName = fruiteName;
        this.totalQuantity = totalQuantity;
    }

    @Column(nullable = false)
    private int vendorId;

    @Column(nullable = false)
    private String fruiteName;

    @Column(nullable = false)
    private int totalQuantity;


    public FruiteStock() {

    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getFruiteName() {
        return fruiteName;
    }

    public void setFruiteName(String fruiteName) {
        this.fruiteName = fruiteName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
