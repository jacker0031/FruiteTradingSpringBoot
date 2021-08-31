package com.example.fruitetrading.controllers;

import com.example.fruitetrading.dao.FruiteVendorDao;
import com.example.fruitetrading.dto.FruitePurchaseDTO;
import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruitePurchaseDetailsNotFound;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;
import com.example.fruitetrading.exception.StockForRequestedFruiteisNotPresentException;
import com.example.fruitetrading.services.FruitePurchaseService;
import com.example.fruitetrading.services.SellService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tradings/fruitevendors")
public class TradingContoller {

    @Autowired
    FruiteVendorDao fruiteVendorDao;

    @Autowired
    FruitePurchaseService fruitePurchaseService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SellService sellService;

    @GetMapping("/{fruitevendorid}/profit")
    public ResponseEntity getProfitBasedOnVendorId(@PathVariable(name = "fruitevendorid") int vendorId) throws FruiteVendorNotFoundException {
        FruiteVendor fruiteVendor=fruiteVendorDao.findById(vendorId).orElseThrow(()-> new FruiteVendorNotFoundException("Fruite VendorNotFound"));
        return new ResponseEntity(fruiteVendor.getProfit(),HttpStatus.OK);
    }

    @GetMapping("/{fruitevendorid}/{fruitename}")
    public ResponseEntity getFruitePurchaseDetailBasedOnVendorIdAndFruiteName(@PathVariable(name="fruitevendorid") int vendorId,@PathVariable(name = "fruitename") String fruiteName) throws FruiteVendorNotFoundException {
        FruiteVendor fruiteVendor=fruiteVendorDao.findById(vendorId).orElseThrow(()-> new FruiteVendorNotFoundException("Fruite VendorNotFound"));

        List<FruitePurchase> fruitePurchaseList=fruitePurchaseService.getAllFruitePurchaseByFruiteVendorAndFruiteName(fruiteVendor,fruiteName);
        List<FruitePurchaseDTO> fruitePurchaseDTOList=new ArrayList<>();
        for(FruitePurchase fruitePurchase:fruitePurchaseList)
        {
            FruitePurchaseDTO fruitePurchaseDTO=convertToFruitePurchaseDTO(fruitePurchase);
            fruitePurchaseDTOList.add(fruitePurchaseDTO);
        }
        return new ResponseEntity(fruitePurchaseDTOList, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{fruitevendorid}/buy",produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buyFruite(@RequestBody FruitePurchaseDTO fruitePurchaseDTO,@PathVariable(name = "fruitevendorid") int vendorId) throws FruiteVendorNotFoundException {
        fruitePurchaseDTO.setFruiteVendorId(vendorId);
        FruitePurchase fruitePurchase=convertToFruitePurchase(fruitePurchaseDTO);
        FruitePurchase savedFruitePurchase=fruitePurchaseService.acceptFruitePurchaseDetails(fruitePurchase);
        FruitePurchaseDTO responseBody=convertToFruitePurchaseDTO(savedFruitePurchase);
        return new ResponseEntity(responseBody,HttpStatus.CREATED);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{fruitevendorid}/sell",produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sellFruite(@RequestBody FruitePurchaseDTO fruitePurchaseDTO,@PathVariable(name = "fruitevendorid") int vendorId) throws FruitePurchaseDetailsNotFound, FruiteVendorNotFoundException, StockForRequestedFruiteisNotPresentException {
        fruitePurchaseDTO.setFruiteVendorId(vendorId);
        FruitePurchase fruitePurchase=convertToFruitePurchase(fruitePurchaseDTO);
        boolean savedFruitePurchase=sellService.sellFruite(fruitePurchase.getFruiteVendor(),fruitePurchase.getFruiteName(),fruitePurchase.getQuantity(),fruitePurchase.getPrice());
        boolean responseBody=savedFruitePurchase;
        return new ResponseEntity(responseBody,HttpStatus.CREATED);
    }
    private FruitePurchaseDTO convertToFruitePurchaseDTO(FruitePurchase fruitePurchase)
    {
        FruitePurchaseDTO fruitePurchaseDTO=modelMapper.map(fruitePurchase,FruitePurchaseDTO.class);
        return  fruitePurchaseDTO;
    }
    private FruitePurchase convertToFruitePurchase(FruitePurchaseDTO fruitePurchaseDTO)
    {
        FruitePurchase fruitePurchase=modelMapper.map(fruitePurchaseDTO,FruitePurchase.class);
        return  fruitePurchase;
    }




}
