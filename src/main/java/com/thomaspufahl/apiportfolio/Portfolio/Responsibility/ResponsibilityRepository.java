package com.thomaspufahl.apiportfolio.Portfolio.Responsibility;

import com.thomaspufahl.apiportfolio.Portfolio.Employment.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsibilityRepository extends JpaRepository<Responsibility, Integer> {
    List<Responsibility> findAllByEmployment(Employment employment);
    void deleteAllByEmployment(Employment employment);
}
