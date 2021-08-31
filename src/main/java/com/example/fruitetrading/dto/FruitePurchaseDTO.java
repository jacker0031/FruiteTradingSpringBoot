package com.example.fruitetrading.dto;

public class FruitePurchaseDTO {
    private int purchaseId;
    private int fruiteVendorId;
    private int price;
    private int quantity;
    private String fruiteName;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getFruiteVendorId() {
        return fruiteVendorId;
    }

    public void setFruiteVendorId(int fruiteVendorId) {
        this.fruiteVendorId = fruiteVendorId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFruiteName() {
        return fruiteName;
    }

    public void setFruiteName(String fruiteName) {
        this.fruiteName = fruiteName;
    }

    @Override
    public String toString() {
        return "FruitePurchaseDTO{" +
                "purchaseId=" + purchaseId +
                ", fruiteVendorId=" + fruiteVendorId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", fruiteName='" + fruiteName + '\'' +
                '}';
    }
}
