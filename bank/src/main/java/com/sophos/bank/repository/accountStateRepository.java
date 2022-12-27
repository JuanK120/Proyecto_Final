package com.sophos.bank.repository;


import com.sophos.bank.entity.accountState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface accountStateRepository extends JpaRepository<accountState, Integer> {
}
