package com.disneyApp.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fanny
 */
@Entity
@Table( name = "genders" )
@Getter
@Setter
public class GendersEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String nombre;

    private String imagen;

    @Column(name = "films_asoc")
    private String filmsAsoc;

//    private boolean deleted = Boolean.FALSE;
    
    @ManyToMany( mappedBy = "genders", cascade = CascadeType.ALL)
    private List<FilmsEntity> films = new ArrayList<>();
}
