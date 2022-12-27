package com.sophos.bank.repository;

import com.sophos.bank.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usersRepository extends JpaRepository<users,Integer>{
}
