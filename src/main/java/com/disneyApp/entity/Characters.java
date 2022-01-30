package com.disneyApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String age;

    private Integer weight;

    private String history;

    @Column(name = "films_A")
    private String filmsA;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany( mappedBy = "characters", cascade = CascadeType.ALL)
    private List<Films> filmsCharacters = new ArrayList();

}
