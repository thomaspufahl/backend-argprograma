package com.thomaspufahl.apiportfolio.Portfolio.Project;

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
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String link;
    private Date finish;
    private String img;

    @ManyToOne
    private Person person;

    public Project(String img) {
        this.img = img;
    }
}
