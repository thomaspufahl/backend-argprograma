package com.thomaspufahl.apiportfolio.Portfolio.Person;

import com.thomaspufahl.apiportfolio.Portfolio.Education.Education;
import com.thomaspufahl.apiportfolio.Portfolio.Employment.Employment;
import com.thomaspufahl.apiportfolio.Portfolio.Project.Project;
import com.thomaspufahl.apiportfolio.Portfolio.Skill.Skill;
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
    @Column(length = 1300)
    private String description;
    private String avatar;
    private String banner;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "person")
    private Set<Employment> employments;

    @OneToMany(mappedBy = "person")
    private Set<Education> educations;

    @OneToMany(mappedBy = "person")
    private Set<Skill> skills;

    @OneToMany(mappedBy = "person")
    private Set<Project> projects;

    public Person(String avatar, String banner) {
        if (avatar!=null && avatar.length()>1) { setAvatar(avatar); }
        if (banner!=null && banner.length()>1) { setBanner(banner); }
    }
    public Person(User user) {
        this.user = user;
    }
}
