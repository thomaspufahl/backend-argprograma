package com.thomaspufahl.apiportfolio.PortfolioData.Person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String description;

    Person(String firstname, String lastname, String description) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
    }
}
