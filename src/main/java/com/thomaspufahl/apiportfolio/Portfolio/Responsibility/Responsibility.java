package com.thomaspufahl.apiportfolio.Portfolio.Responsibility;

import com.thomaspufahl.apiportfolio.Portfolio.Employment.Employment;
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
public class Responsibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @ManyToOne()
    private Employment employment;
}
