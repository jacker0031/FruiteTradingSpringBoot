package com.example.fruitetrading.controllers;

import com.example.fruitetrading.dao.FruiteVendorDao;
import com.example.fruitetrading.dto.FruiteVendorDTO;
import com.example.fruitetrading.entities.FruiteVendor;
import com.example.fruitetrading.exception.FruiteVendorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fruitevendors")
public class FruiteVendorController {

    @Autowired
    FruiteVendorDao fruiteVendorDao;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity allfruiteVendors()
    {
        List<FruiteVendor> fruiteVendorList=fruiteVendorDao.findAll();
        List<FruiteVendorDTO> fruiteVendorDTOList = new ArrayList<>();
        for(FruiteVendor fruiteVendor:fruiteVendorList)
        {
            fruiteVendorDTOList.add(convertToFruiteVendorDTO(fruiteVendor));
        }
        return new ResponseEntity(fruiteVendorDTOList, HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createFruiteVendor(@RequestBody FruiteVendorDTO fruiteVendorDTO)
    {
        FruiteVendor fruiteVendor=convertToFruiteVendor(fruiteVendorDTO);
        FruiteVendor savedFruiteVendor=fruiteVendorDao.save(fruiteVendor);
        FruiteVendorDTO responseBody=convertToFruiteVendorDTO(savedFruiteVendor);
        return new ResponseEntity(responseBody,HttpStatus.CREATED);
    }
    @GetMapping("/{fruiteVendorId}")
    public ResponseEntity getFruiteVendorBasedOnId(@PathVariable(name = "fruiteVendorId") int vendorId) throws FruiteVendorNotFoundException {
        FruiteVendor fruiteVendor=fruiteVendorDao.findById(vendorId).orElseThrow(()->new FruiteVendorNotFoundException("FruiteVendor Not Found"));
        FruiteVendorDTO fruiteVendorDTO=convertToFruiteVendorDTO(fruiteVendor);
        return new ResponseEntity(fruiteVendorDTO,HttpStatus.OK);
    }
    private FruiteVendorDTO convertToFruiteVendorDTO(FruiteVendor fruiteVendor)
    {
        FruiteVendorDTO fruiteVendorDTO=modelMapper.map(fruiteVendor,FruiteVendorDTO.class);
        return  fruiteVendorDTO;
    }
    private FruiteVendor convertToFruiteVendor(FruiteVendorDTO fruiteVendorDTO)
    {
        FruiteVendor fruiteVendor=modelMapper.map(fruiteVendorDTO,FruiteVendor.class);
        return  fruiteVendor;
    }

}

