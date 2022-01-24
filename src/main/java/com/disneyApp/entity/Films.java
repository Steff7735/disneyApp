package com.disneyApp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name ="films")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@Getter
@Setter
public class Films {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String image;

    private String title;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    private Integer stars;

   private boolean deleted = Boolean.FALSE;


     @ManyToMany(
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(
            name = "genders_films",
            joinColumns = @JoinColumn(name = "genders_id"),
            inverseJoinColumns = @JoinColumn(name = "films_id"))
   
     private List<Genders> genders = new ArrayList<>();

    @ManyToMany(
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(
            name = "films_characters",
            joinColumns = @JoinColumn(name = "films_id"),
            inverseJoinColumns = @JoinColumn(name = "characters_id"))

    private List<Characters> characters = new ArrayList<>();

    public void addCharactersToFilms(Characters charactersToBeAdded) {
        this.characters.add(charactersToBeAdded);
    }

    public void removeCharactersFromFilms(Characters charactersToBeRemoved) {
        this.characters.remove(charactersToBeRemoved);
    }


    public void addGendersToFilms(Genders gendersToBeAdded) {

        this.genders.add(gendersToBeAdded);
    }

    public void removeGendersFromFilms(Genders gendersToBeRemoved) {

        this.genders.remove(gendersToBeRemoved);
    }
}
