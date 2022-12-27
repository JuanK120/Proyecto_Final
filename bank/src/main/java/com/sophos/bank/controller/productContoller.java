package com.sophos.bank.controller;


import com.sophos.bank.entity.product;
import com.sophos.bank.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productContoller {

    @Autowired
    productService productService;

    @GetMapping
    public ResponseEntity<List<product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<product> getProduct(@PathVariable("id") int id){
        return productService.getProductById(id).map(
                product -> new ResponseEntity<>(product, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<product> createProduct(@RequestBody product product){
        return new ResponseEntity<>(productService.createProduct(product),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") int id){
        if (productService.deleteProductById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
