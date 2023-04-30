package com.thomaspufahl.apiportfolio.Portfolio.Responsibility;

import com.thomaspufahl.apiportfolio.Portfolio.Employment.Employment;

import java.util.List;
import java.util.Optional;

public interface ResponsibiltyManager {
    List<Responsibility> all();
    List<Responsibility> allByEmployment(Employment employment);
    Optional<Responsibility> getById(Integer responsibility_id);
    void create(Responsibility responsibility);
    Optional<Responsibility> editById(Integer responsibility_id, Responsibility responsibility);
    void delete();
    void deleteById(Integer responsibility_id);
    void deleteAllByEmployment(Employment employment);
}
