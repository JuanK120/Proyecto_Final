package com.sophos.bank.controller;

import com.sophos.bank.entity.movementType;
import com.sophos.bank.service.movementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/movementType")
public class movementTypeContoller {
    @Autowired
    movementTypeService movementTypeService;

    @GetMapping
    public ResponseEntity<List<movementType>> getMovementTypes(){
        return new ResponseEntity<>(movementTypeService.getAllMovementTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<movementType> getMovementType(@PathVariable("id") int id){
        return movementTypeService.getMovementTypeById(id).map(
                movementType -> new ResponseEntity<>(movementType, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<movementType> createMovementType(@RequestBody movementType movementType){
        return new ResponseEntity<>(movementTypeService.createMovementType(movementType),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovementTypeById(@PathVariable("id") int id){
        if (movementTypeService.deleteMovementTypeById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
