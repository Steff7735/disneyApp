package com.disneyApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genders")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genders SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Genders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String image;

    @Column(name = "films_A")
    private String filmsA;

    private boolean deleted = Boolean.FALSE;

    @ManyToMany( mappedBy = "genders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Films> films = new ArrayList<>();

}
