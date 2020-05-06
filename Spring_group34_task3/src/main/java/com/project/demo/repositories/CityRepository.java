package com.project.demo.repositories;

import com.project.demo.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);
    Optional<City> findById(Long id);
}
