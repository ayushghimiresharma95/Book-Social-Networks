package com.example.BookNetworkServer.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositary extends JpaRepository<Role, Integer > {

    Optional<Role> findByName(String role)  ;
}
