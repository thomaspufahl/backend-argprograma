package com.thomaspufahl.apiportfolio.Portfolio.Skill;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
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
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private float percentage;

    @ManyToOne
    private Person person;
}
