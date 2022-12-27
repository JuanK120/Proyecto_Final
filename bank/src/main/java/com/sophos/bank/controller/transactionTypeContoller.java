package com.sophos.bank.controller;


import com.sophos.bank.entity.transactionType;
import com.sophos.bank.service.transactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactionType")
public class transactionTypeContoller {
    @Autowired
    transactionTypeService transactionTypeService;

    @GetMapping
    public ResponseEntity<List<transactionType>> getTransactionTypes(){
        return new ResponseEntity<>(transactionTypeService.getAllTransactionType(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<transactionType> getTransactionType(@PathVariable("id") int id){
        return transactionTypeService.getTransactionTypeById(id).map(
                A -> new ResponseEntity<>(A, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<transactionType> createTransactionType(@RequestBody transactionType transactionType){
        return new ResponseEntity<>(transactionTypeService.createTransactionType(transactionType),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransactionTypeById(@PathVariable("id") int id){
        if (transactionTypeService.deleteTransactionType(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
