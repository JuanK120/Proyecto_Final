package com.sophos.bank.controller;


import com.sophos.bank.entity.client;
import com.sophos.bank.entity.users;
import com.sophos.bank.service.clientService;
import com.sophos.bank.service.productService;
import com.sophos.bank.service.usersService;
import com.sophos.bank.validations.clientValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/client")
public class clientContoller{

    @Autowired
    usersService usersService;

    @Autowired
    clientService clientService;

    @Autowired
    productService productService;

    clientValidations clientValidations = new clientValidations();

    @GetMapping
    public ResponseEntity<List<client>> getClients(){
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<client> getClient(@PathVariable("id") int id){
        return clientService.getClientById(id).map(
                client -> new ResponseEntity<>(client, HttpStatus.OK)
        ).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<client> createClient(@RequestBody client client){
        System.out.println(client.getIdType()+" | "+client.getIdNumber()+" | "+client.getName()+" | "+client.getLastName()+" | "+
                client.getEmail()+" | "+client.getBirthDate()+" | "+
                client.getModificationDate()+" | "+client.getModificationUser()+" | "+client.isActive());

        if (clientValidations.isOfAge(client.getBirthDate()) && clientValidations.isEmail(client.getEmail())
            && clientValidations.is2CharLong(client.getName()) && clientValidations.is2CharLong(client.getLastName())
        ){
            client client1 = client;
            client1.setCreationDate(new Date(System.currentTimeMillis()));
            client1.setModificationDate(new Date(System.currentTimeMillis()));
            users admin = new users();
            admin.setUserId(1);
            admin.setUserName("admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword("123456");
            admin.setActive(true);
            if (client1.getCreationUser() == null) {
                client1.setCreationUser(admin);
            }
            if (client1.getModificationUser() == null) {
                client1.setModificationUser(admin);
            }
            return new ResponseEntity<>(clientService.createClient(client1),HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public ResponseEntity<client> updateClient(@RequestBody client client){
        if (clientValidations.isOfAge(client.getBirthDate()) && clientValidations.isEmail(client.getEmail())
                && clientValidations.is2CharLong(client.getName()) && clientValidations.is2CharLong(client.getLastName())
        ){
            client client1 = client;
            client1.setModificationDate(new Date(System.currentTimeMillis()));
            return new ResponseEntity<>(clientService.updateClient(client1),HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClientById(@PathVariable("id") int id){
        if (clientValidations.isDeletable(productService.findAllByOwner(clientService.getClientById(id)))){
            if (clientService.deleteClientById(id)){
                return new ResponseEntity<>(HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
