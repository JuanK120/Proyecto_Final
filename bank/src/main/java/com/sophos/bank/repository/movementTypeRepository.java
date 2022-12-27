package com.sophos.bank.repository;

import com.sophos.bank.entity.movementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface movementTypeRepository extends JpaRepository<movementType,Integer>{
}
