package com.thomaspufahl.apiportfolio.Portfolio.Employment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Integer> {
    Optional<Employment> findByName(String name);
    boolean existsByName(String name);
}
