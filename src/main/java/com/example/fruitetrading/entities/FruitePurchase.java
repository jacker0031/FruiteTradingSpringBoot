package com.example.fruitetrading.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class FruitePurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int purchaseId;

    public FruitePurchase() {

    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getFruiteName() {
        return fruiteName;
    }

    public void setFruiteName(String fruiteName) {
        this.fruiteName = fruiteName;
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

    public FruiteVendor getFruiteVendor() {
        return fruiteVendor;
    }

    public void setFruiteVendor(FruiteVendor fruiteVendor) {
        this.fruiteVendor = fruiteVendor;
    }

    @ManyToOne
    @JoinColumn(name="vendor_id",nullable = false)
    @JsonManagedReference
    private FruiteVendor fruiteVendor;

    @Column(nullable = false)
    private String fruiteName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;


}
