package com.thomaspufahl.apiportfolio.Portfolio.Education;

import com.thomaspufahl.apiportfolio.Portfolio.Person.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String degree;
    private String school;
    private String description;
    private Date start;
    private Date end;

    @ManyToOne
    private Person person;

}
