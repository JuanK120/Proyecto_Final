package com.sophos.bank.repository;


import com.sophos.bank.entity.client;
import com.sophos.bank.entity.identificationType;
import com.sophos.bank.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface clientRepository extends JpaRepository<client, Integer>  {

    @Modifying
    @Query("UPDATE client  SET identificationType=?2,idNumber=?3,name=?4,lastName=?5,email=?6,birthDate=?7,creationDate=?8,modificationDate=?9,users=?10,active=?11 where clientId=?1")
    void setClientInfoById(int id, identificationType idType, int idNumber, String name, String lastName,
                             String email, Date birthDate, Date creationDate, Date modificationDate,
                             users modificationUser, boolean active);
}
