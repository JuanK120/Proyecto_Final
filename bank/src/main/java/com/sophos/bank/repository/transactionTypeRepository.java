package com.sophos.bank.repository;

import com.sophos.bank.entity.transactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface transactionTypeRepository extends JpaRepository<transactionType, Integer>{
}
