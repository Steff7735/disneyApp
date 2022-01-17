package com.disneyApp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fanny
 */
@Entity
@Table(name = "films")
@Getter
@Setter
public class FilmsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String image;

    private String title;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    private Integer stars;
    
    @Column(name = "characters_asoc")
    private String charactersAsoc;

//    private boolean deleted = Boolean.FALSE;
    
//    @ManyToMany(
//            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "genders_id", insertable = false, updatable = false)
//    private GendersEntity gendersEntity;
//
//    @Column(name = "genders_id", nullable = false)
//    private String GendersId;

     @ManyToMany(
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(
            name = "genders_films",
            joinColumns = @JoinColumn(name = "genders_id"),
            inverseJoinColumns = @JoinColumn(name = "films_id"))
   
     private List<GendersEntity> genders = new ArrayList<>();

    @ManyToMany(
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(
            name = "films_characters",
            joinColumns = @JoinColumn(name = "films_id"),
            inverseJoinColumns = @JoinColumn(name = "characters_id"))

    private List<CharactersEntity> characters = new ArrayList<>();
}
