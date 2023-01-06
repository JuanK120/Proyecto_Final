package com.sophos.bank.repository;

import com.sophos.bank.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface productRepository extends JpaRepository<product,Integer> {

    @Query("FROM product p WHERE p.owner = ?1 and p.gmfExempt = ?2")
    List<product> findByOwnerAndGmfExempt(client client, boolean exempt);
    @Query("FROM product p WHERE p.owner = ?1")
    List<product> findAllByOwner(Optional<client> client);
    @Modifying
    @Query("UPDATE product  SET owner=?2,productType=?3,productNumber=?4,state=?5,balance=?6,availableBalance=?7, gmfExempt=?8 ,modificationDate=?9,modificationUser=?10 where productId=?1")
    void setProductInfoById(int productId, client owner, productType productType, BigInteger productNumber, accountState state, int balance,
                            int availableBalance, boolean exempt, Date modificationDate,
                            users modificationUser);

    List<product> findAllByProductNumber(BigInteger productNumber);
}
