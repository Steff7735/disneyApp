package com.disneyApp.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author fanny
 */
@Entity
@Table(name ="characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Getter
@Setter
public class Characters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String age;

    private Integer weight;

    private String history;

    @Column(name = "films_asoc")
    private String filmsAsoc;

   //SOFT DELETE
    private boolean deleted = Boolean.FALSE;
    
    @ManyToMany( mappedBy = "filmsCharacters", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Films> filmsCharacters = new ArrayList<>();

}
