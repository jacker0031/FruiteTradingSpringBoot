package com.example.fruitetrading.dto;

public class FruiteStockDTO {
    private int stockId;
    private int vendorId;
    private String fruiteName;
    private int totalQuantity;

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

    @Override
    public String toString() {
        return "FruiteStockDTO{" +
                "stockId=" + stockId +
                ", vendorId=" + vendorId +
                ", fruiteName='" + fruiteName + '\'' +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}
