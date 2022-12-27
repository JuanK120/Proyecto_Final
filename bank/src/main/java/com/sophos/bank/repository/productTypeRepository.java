package com.sophos.bank.repository;

import com.sophos.bank.entity.productType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productTypeRepository extends JpaRepository<productType,Integer>{
}
