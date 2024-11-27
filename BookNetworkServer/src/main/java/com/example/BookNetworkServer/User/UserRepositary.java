package com.example.BookNetworkServer.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepositary extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email) ;
    Optional<User> findById(Integer id); 
}


