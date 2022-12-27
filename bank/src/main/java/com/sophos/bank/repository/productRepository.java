package com.sophos.bank.repository;

import com.sophos.bank.entity.client;
import com.sophos.bank.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends JpaRepository<product,Integer> {
    List<product> findByProductIdAndGmfExempt(int productId, boolean exempt);
    List<product> findAllByOwner(int productId);
}
