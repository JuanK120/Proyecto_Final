package com.sophos.bank.repository;

import com.sophos.bank.entity.identificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface identificationTypeRepository extends JpaRepository<identificationType, Integer>{
}
