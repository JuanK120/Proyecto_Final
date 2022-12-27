package com.sophos.bank.repository;

import com.sophos.bank.entity.transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface transactionRepository extends JpaRepository<transaction,Integer>{
}
