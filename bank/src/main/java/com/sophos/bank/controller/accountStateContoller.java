package com.sophos.bank.controller;

import com.sophos.bank.entity.accountState;
import com.sophos.bank.service.accountStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountState")
public class accountStateContoller {

    @Autowired
    accountStateService accountStateService;

    @GetMapping
    public ResponseEntity<List<accountState>> getAccountStates(){
        return new ResponseEntity<>(accountStateService.getAllAccountStates(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<accountState> getAccountState(@PathVariable("id") int id){
        return accountStateService.getAccountStateById(id).map(
                accountState -> new ResponseEntity<>(accountState, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<accountState> createAccountState(@RequestBody accountState accountState){
        return new ResponseEntity<>(accountStateService.createAccountState(accountState),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAccountStateById(@PathVariable("id") int id){
         if (accountStateService.deleteAccountStateById(id)){
             return new ResponseEntity<>(HttpStatus.OK);
         }else {
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }

}
