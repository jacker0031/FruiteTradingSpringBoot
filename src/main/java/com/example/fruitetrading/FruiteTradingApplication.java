package com.example.fruitetrading;

import com.example.fruitetrading.dao.FruiteVendorDao;
import com.example.fruitetrading.entities.FruitePurchase;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruitePurchaseDetailsNotFound;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;
import com.example.fruitetrading.exception.StockForRequestedFruiteisNotPresentException;
import com.example.fruitetrading.services.FruitePurchaseService;
import com.example.fruitetrading.services.InitService;
import com.example.fruitetrading.services.SellService;
import com.example.fruitetrading.services.ShowProfitService;
import com.example.fruitetrading.services.impl.SellServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class FruiteTradingApplication {


    @Autowired
    SellService sellService;

    @Autowired
    FruitePurchaseService fruitePurchaseService;

    @Autowired
    ShowProfitService showProfitService;

    @Autowired
    FruiteVendorDao fruiteVendorDao;

    public static void main(String[] args) throws FruitePurchaseDetailsNotFound, FruiteVendorNotFoundException, StockForRequestedFruiteisNotPresentException {
        ApplicationContext ctx=SpringApplication.run(FruiteTradingApplication.class, args);
        SellService sellService=ctx.getBean(SellService.class);
        FruiteVendorDao fruiteVendorDao=ctx.getBean(FruiteVendorDao.class);
        FruitePurchaseService fruitePurchaseService=ctx.getBean(FruitePurchaseService.class);
//        sellService.sellFruite(fruiteVendorDao.findByVendorName("Jenish1").orElseThrow(()->new FruiteVendorNotFoundException("FruiteVendor NotFound")),"APPLE",100,200);
//        sellService.sellFruite(fruiteVendorDao.findByVendorName("Jenish").orElseThrow(()->new FruiteVendorNotFoundException("FruiteVendor Not Found")),"APPLE",60,200);
     //   System.out.println(fruitePurchaseService.getAllFruitePurchase());

    }


    @Bean
    CommandLineRunner init(InitService initService){

        return args -> {
            initService.init();
        };
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
