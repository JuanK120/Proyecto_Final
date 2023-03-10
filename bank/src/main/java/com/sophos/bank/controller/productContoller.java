package com.sophos.bank.controller;


import com.sophos.bank.entity.accountState;
import com.sophos.bank.entity.product;
import com.sophos.bank.entity.users;
import com.sophos.bank.service.clientService;
import com.sophos.bank.service.productService;
import com.sophos.bank.service.usersService;
import com.sophos.bank.validations.productValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class productContoller {

    @Autowired
    productService productService;

    @Autowired
    clientService clientService;

    @Autowired
    usersService usersService;

    productValidations productValidations = new productValidations();

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

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<product>> getProductsByOwner(@PathVariable("id") int id){
        return new ResponseEntity<>(productService.findAllByOwner(clientService.getClientById(id)), HttpStatus.OK);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<List<product>> getProductsByAccountNumber(@PathVariable("number") BigInteger id){
        return new ResponseEntity<>(productService.getProductByProductNumber(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<product> createProduct(@RequestBody product product){
        product prod = product;
        if (
                (
                        prod.isGmfExempt() == true
                ) && (
                        !productValidations.noExemptAccounts(
                                productService.getProductByOwnerAndGmfexempt(prod.getOwner(),true))
                )
        ) {
            prod.setGmfExempt(false);
        }
        if (prod.getProductType().getIdType()==1){
            // Balance of savings account can not be less than 0
            if (prod.getBalance() < 0){
                return new ResponseEntity<>(product,HttpStatus.BAD_REQUEST);
            }

            // generation of a new product number
            BigInteger newAccountNumber=BigInteger.valueOf(Long.parseLong(productValidations.randomString(prod.getProductType().getIdType())));
            while (!productService.getProductByProductNumber(newAccountNumber).isEmpty()){
                newAccountNumber=BigInteger.valueOf(Long.parseLong(productValidations.randomString(prod.getProductType().getIdType())));
            }
            prod.setProductNumber(newAccountNumber);
            // calculation of available balance
            int availableBalance = productValidations.gmfValue(prod.getBalance(),prod.isGmfExempt());
            prod.setAvailableBalance(availableBalance);

        } else if(prod.getProductType().getIdType()==2){

            // Balance of checking account can not be less than -3000000
            if (prod.getBalance() < -3000000){
                return new ResponseEntity<>(product,HttpStatus.BAD_REQUEST);
            }

            // generation of a new product number
            BigInteger newAccountNumber=BigInteger.valueOf(Long.parseLong(productValidations.randomString(prod.getProductType().getIdType())));
            while (!productService.getProductByProductNumber(newAccountNumber).isEmpty()){
                newAccountNumber=BigInteger.valueOf(Long.parseLong(productValidations.randomString(prod.getProductType().getIdType())));
            }
            prod.setProductNumber(newAccountNumber);
            // calculation of available balance
            int availableBalance = productValidations.gmfValue(prod.getBalance(),prod.isGmfExempt());
            prod.setAvailableBalance(availableBalance);

        } else {
            return new ResponseEntity<>(product,HttpStatus.BAD_REQUEST);
        }
        // always active state
        accountState state= new accountState();
        state.setIdState(1);
        state.setStateName("Active");
        prod.setState(state);
        prod.setCreationDate(new Date(System.currentTimeMillis()));
        prod.setModificationDate(new Date(System.currentTimeMillis()));
        // creation and uptate user is admin by default
        users admin = usersService.getUserById(1).get();
        if (prod.getCreationUser() == null) {
            prod.setCreationUser(admin);
        }
        if (prod.getModificationUser() == null) {
            prod.setModificationUser(admin);
        }
        return new ResponseEntity<>(productService.createProduct(prod),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") int id){
        if (productService.deleteProductById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin("*")
    @PutMapping("/exemptAccount/{id}")
    public ResponseEntity exemptAccount(@PathVariable("id") int id, @RequestBody users user){
        product prod = productService.getProductById(id).get();
        if (
                (
                        prod.isGmfExempt() == false
                ) && (
                        productValidations.noExemptAccounts(
                                productService.getProductByOwnerAndGmfexempt(prod.getOwner(),true))
                )
        ){
            prod.setGmfExempt(true);
            prod.setModificationDate(new Date(System.currentTimeMillis()));
            prod.setModificationUser(user);
            productService.updateProduct(prod);
            return new  ResponseEntity<>(HttpStatus.OK);
        } else if (prod.isGmfExempt() == true){
            prod.setGmfExempt(false);
            prod.setModificationDate(new Date(System.currentTimeMillis()));
            prod.setModificationUser(user);
            productService.updateProduct(prod);
            return new  ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin("*")
    @PutMapping("/cancelAccount/{id}")
    public ResponseEntity cancelAccount(@PathVariable("id") int id,@RequestBody users user){
        product prod = productService.getProductById(id).get();
        if (prod.getBalance() == 0){
            accountState state=new accountState();
            state.setIdState(3);
            state.setStateName("Canceled");
            prod.setState(state);
            prod.setModificationDate(new Date(System.currentTimeMillis()));
            prod.setModificationUser(user);
            productService.updateProduct(prod);
            return new  ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
