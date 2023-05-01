package com.thomaspufahl.apiportfolio.Portfolio.Person;

import com.thomaspufahl.apiportfolio.Portfolio.Education.Education;
import com.thomaspufahl.apiportfolio.Portfolio.Employment.Employment;
import com.thomaspufahl.apiportfolio.Security.model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


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

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "person")
    private Set<Employment> employment;

    @OneToMany(mappedBy = "person")
    private Set<Education> educations;

    public Person(User user) {
        this.user = user;
    }
}
