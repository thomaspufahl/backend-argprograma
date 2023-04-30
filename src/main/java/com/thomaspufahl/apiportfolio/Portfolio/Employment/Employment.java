package com.thomaspufahl.apiportfolio.Portfolio.Employment;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import com.thomaspufahl.apiportfolio.Portfolio.Responsibility.Responsibility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String position;
    private String employeer;
    private Date start;
    private Date end;

    @ManyToOne
    private Person person;

    @OneToMany(mappedBy = "employment")
    private Set<Responsibility> responsibilities;

    public Employment(Integer id) {
        this.id = id;
    }
}
