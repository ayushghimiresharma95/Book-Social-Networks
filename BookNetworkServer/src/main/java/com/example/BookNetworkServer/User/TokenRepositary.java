package com.example.BookNetworkServer.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepositary extends JpaRepository<Token,Integer> {
    Optional<Token> findByToken(String token) ;
}
