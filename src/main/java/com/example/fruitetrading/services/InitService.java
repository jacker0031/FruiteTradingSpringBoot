package com.example.fruitetrading.services;

import com.example.fruitetrading.exception.FruiteVendorNotFoundException;

public interface InitService {
    public void init() throws FruiteVendorNotFoundException;
}
