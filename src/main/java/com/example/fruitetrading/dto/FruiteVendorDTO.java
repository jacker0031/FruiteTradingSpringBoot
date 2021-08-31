package com.example.fruitetrading.dto;

public class FruiteVendorDTO {
    private int vendorId;
    private int profit;
    private String vendorName;

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @Override
    public String toString() {
        return "FruiteVendorDTO{" +
                "vendorId=" + vendorId +
                ", fruitePurchaseId=" +
                ", profit=" + profit +
                ", vendorName='" + vendorName + '\'' +
                '}';
    }
}
