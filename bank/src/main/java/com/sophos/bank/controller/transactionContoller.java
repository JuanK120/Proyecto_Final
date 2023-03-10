package com.sophos.bank.controller;


import com.sophos.bank.entity.product;
import com.sophos.bank.entity.transaction;
import com.sophos.bank.service.productService;
import com.sophos.bank.service.transactionService;
import com.sophos.bank.validations.productValidations;
import com.sophos.bank.validations.transactionValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/transaction")
public class transactionContoller {

    productValidations productValidations= new productValidations();
    transactionValidations transactionValidations = new transactionValidations();

    @Autowired
    transactionService transactionService;

    @Autowired
    productService productService;

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

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<List<transaction>> getTransaction(@PathVariable("accountNumber") BigInteger accountNumber){
        return new ResponseEntity<>(transactionService.getAllTransactionsByAccount(accountNumber), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<transaction> createTransaction(@RequestBody transaction transaction){
        transaction transac = transaction;
        product prod = transaction.getTargetProduct();
        if (transac.getTransactionType().getIdType()==1){
            int newBalance = prod.getBalance()+transac.getAmount();
            if ((prod.getProductType().getIdType()==1 && newBalance < 0) ||
                    (prod.getProductType().getIdType()==1 && newBalance < -3000000)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                prod.setBalance(newBalance);
                prod.setAvailableBalance(productValidations.gmfValue(prod.getBalance(), prod.isGmfExempt()));
                transac.setBalance(prod.getBalance());
                transac.setAvailableBalance(productValidations.gmfValue(prod.getBalance(), prod.isGmfExempt()));
                transac.setMovementDate(new Date(System.currentTimeMillis()));
                productService.updateProduct(prod);
                return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.CREATED);
            }
        } else if (transac.getTransactionType().getIdType()==2){
            if (transactionValidations.transactionIsViable(prod.getBalance(),transac.getAmount())) {
                int newBalance = prod.getBalance() - transac.getAmount();
                prod.setBalance(newBalance);
                transac.setBalance(prod.getBalance());
                transac.setAvailableBalance(productValidations.gmfValue(prod.getBalance(), prod.isGmfExempt()));
                transac.setMovementDate(new Date(System.currentTimeMillis()));
                productService.updateProduct(prod);
                return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else if (transac.getTransactionType().getIdType()==3){
            if (transactionValidations.transactionIsViable(prod.getBalance(),transac.getAmount())) {
                int newBalance = prod.getBalance() + transac.getAmount();
                prod.setBalance(newBalance);
                transac.setBalance(prod.getBalance());
                transac.setAvailableBalance(productValidations.gmfValue(prod.getBalance(), prod.isGmfExempt()));
                transac.setMovementDate(new Date(System.currentTimeMillis()));
                productService.updateProduct(prod);
                return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
