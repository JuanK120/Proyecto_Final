package com.sophos.bank.repository;

import com.sophos.bank.entity.transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface transactionRepository extends JpaRepository<transaction,Integer>{

    @Query("from transaction tran where tran.targetProduct.productNumber = ?1")
    List<transaction> findAllByTargetProduct(BigInteger targetProduct);
}
