package com.sophos.bank.controller;


import com.sophos.bank.entity.users;
import com.sophos.bank.service.usersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class userContoller {
    @Autowired
    usersService usersService;

    @GetMapping
    public ResponseEntity<List<users>> getUsers(){
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<users> getUser(@PathVariable("id") int id){
        return usersService.getUserById(id).map(
                user -> new ResponseEntity<>(user, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<users> createUser(@RequestBody users user){
        return new ResponseEntity<>(usersService.createUser(user),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable("id") int id){
        if (usersService.deleteUserById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



    @PutMapping("/login")
    public ResponseEntity<users> login(@RequestBody users user){
        //System.out.println(user.getEmail() + " " + user.getPassword());
        return usersService.getUserByEmailAndPassword(user.getEmail(),user.getPassword()).map(
                userResult -> {
                    System.out.println("here: " + userResult.getUserName() + " " + userResult.getUserId());
                    return new ResponseEntity<users>(userResult, HttpStatus.OK);}
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

}
