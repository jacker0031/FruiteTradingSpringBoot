package com.example.fruitetrading.controllers;

import com.example.fruitetrading.dao.FruiteStockDao;
import com.example.fruitetrading.dao.FruiteVendorDao;
import com.example.fruitetrading.dto.FruiteStockDTO;
import com.example.fruitetrading.dto.FruiteVendorDTO;
import com.example.fruitetrading.entities.FruiteStock;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;
import com.example.fruitetrading.services.FruitePurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fruitestocks")
public class FruiteStockController {

    @Autowired
    FruiteStockDao fruiteStockDao;

    @Autowired
    FruiteVendorDao fruiteVendorDao;

    @Autowired
    FruitePurchaseService fruitePurchaseService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity allVendorsStock()
    {
        List<FruiteStock> fruiteStockList=fruiteStockDao.findAll();
        List<FruiteStockDTO> fruiteStockDTOList=new ArrayList<>();
        for(FruiteStock fruiteStock:fruiteStockList)
        {
            fruiteStockDTOList.add(convertToFruiteStockDTO(fruiteStock));
        }
        return new ResponseEntity(fruiteStockDTOList, HttpStatus.OK);
    }
    private FruiteStockDTO convertToFruiteStockDTO(FruiteStock fruiteStock)
    {
        FruiteStockDTO fruiteStockDTO=modelMapper.map(fruiteStock,FruiteStockDTO.class);
        return  fruiteStockDTO;
    }
    private FruiteStock convertToFruiteStock(FruiteStockDTO fruiteStockDTO)
    {
        FruiteStock fruiteStock=modelMapper.map(fruiteStockDTO,FruiteStock.class);
        return  fruiteStock;
    }

}
