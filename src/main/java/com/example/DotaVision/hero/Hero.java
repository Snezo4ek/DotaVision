package com.example.DotaVision.hero;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "heroes")
public class Hero {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String primary_attr;

    private Integer stat_income;
}
