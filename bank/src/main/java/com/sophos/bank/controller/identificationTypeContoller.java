package com.sophos.bank.controller;

import com.sophos.bank.entity.identificationType;
import com.sophos.bank.service.identificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/identificationType")
public class identificationTypeContoller {
    @Autowired
    identificationTypeService identificationTypeService;

    @GetMapping
    public ResponseEntity<List<identificationType>> getIdentificationTypes(){
        return new ResponseEntity<>(identificationTypeService.getAllIdentificationTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<identificationType> getIdentificationType(@PathVariable("id") int id){
        return identificationTypeService.getIdentificationTypeById(id).map(
                identificationType -> new ResponseEntity<>(identificationType, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<identificationType> createIdentificationType(@RequestBody identificationType identificationType){
        return new ResponseEntity<>(identificationTypeService.createIdentificationType(identificationType),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIdentificationTypeById(@PathVariable("id") int id){
        if (identificationTypeService.deleteIdentificationTypeById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
