package com.sophos.bank.controller;


import com.sophos.bank.entity.transaction;
import com.sophos.bank.service.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class transactionContoller {

    @Autowired
    transactionService transactionService;

    @GetMapping
    public ResponseEntity<List<transaction>> getTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<transaction> getTransaction(@PathVariable("id") int id){
        return transactionService.getTransactionById(id).map(
                transaction -> new ResponseEntity<>(transaction, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<transaction> createTransaction(@RequestBody transaction transaction){
        return new ResponseEntity<>(transactionService.createTransaction(transaction),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTransactionById(@PathVariable("id") int id){
        if (transactionService.deleteTransactionById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
