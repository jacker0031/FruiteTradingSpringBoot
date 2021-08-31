package com.example.fruitetrading.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FruiteVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int vendorId;

    public FruiteVendor() {

    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public Set<FruitePurchase> getFruitePurchases() {
        return fruitePurchases;
    }

    public void setFruitePurchases(Set<FruitePurchase> fruitePurchases) {
        this.fruitePurchases = fruitePurchases;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public FruiteVendor(int vendorId, String vendorName, int profit) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.profit = profit;
    }

    @OneToMany(mappedBy = "fruiteVendor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<FruitePurchase> fruitePurchases;

    @Column(nullable = false)
    private String vendorName;

    @Column(nullable = false)
    private int profit;
}
