package com.sophos.bank.controller;


import com.sophos.bank.entity.productType;
import com.sophos.bank.service.productTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productType")
public class productTypeContoller {

    @Autowired
    productTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<productType>> getProductTypes(){
        return new ResponseEntity<>(productTypeService.getAllProductTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<productType> getProductType(@PathVariable("id") int id){
        return productTypeService.getProductTypeById(id).map(
                productType -> new ResponseEntity<>(productType, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<productType> createProductType(@RequestBody productType productType){
        return new ResponseEntity<>(productTypeService.createProductType(productType),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductTypeById(@PathVariable("id") int id){
        if (productTypeService.deleteProductType(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
