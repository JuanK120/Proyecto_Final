package com.sophos.bank.repository;

import com.sophos.bank.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usersRepository extends JpaRepository<users,Integer>{

    @Query("from users us where us.email= ?1 and us.password = ?2")
    Optional<users> findByEmailAndPassword(String email,String password);
}
