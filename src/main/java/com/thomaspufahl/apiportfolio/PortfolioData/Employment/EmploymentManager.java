package com.thomaspufahl.apiportfolio.PortfolioData.Employment;

import java.util.List;
import java.util.Optional;

public interface EmploymentManager {
    List<Employment> getAll();
    void save(Employment employment);
    void deleteAll();
    void deleteById(Integer id);
    Optional<Employment> getById(Integer id);
    Optional<Employment> editById(Integer id, String name, String description);
    boolean existById(Integer id);
    Optional<Employment> getByName(String name);
    boolean existByName(String name);
}
