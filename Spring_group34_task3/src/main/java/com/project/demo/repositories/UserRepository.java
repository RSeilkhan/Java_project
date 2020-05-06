package com.project.demo.repositories;

import com.project.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface    UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    //Users findById(Long id);
    Optional<User> findById(Long id);
    Optional<User> findByCity(String city);




}
