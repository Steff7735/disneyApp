package com.disneyApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
@Getter
@Setter
@SQLDelete(sql = "UPDATE films SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
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
                    CascadeType.MERGE,
            }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "films_characters",
            joinColumns= @JoinColumn(name = "films_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    
    private List<Characters> characters = new ArrayList<>();

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "films_genders",
            joinColumns= @JoinColumn(name = "films_id"),
            inverseJoinColumns = @JoinColumn(name = "genders_id"))
    
    private List<Genders> genders = new ArrayList<>();

    public void addCharacters(Characters addCharacters) {

        this.characters.add(addCharacters);
    }

    public void addGenders(Genders addGenders) {

        this.genders.add(addGenders);
    }
    public void removeCharacters(Characters charactersToBeRemoved) {

        this.characters.remove(charactersToBeRemoved);
    }
    public void removeGenders(Genders gendersToBeRemoved) {

        this.genders.remove(gendersToBeRemoved);
    }

}
