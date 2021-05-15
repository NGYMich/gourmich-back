package com.easycook.recettes.recette;

import com.easycook.recettes.ingredient.Ingredient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "recette")
public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recette_id")
    private Long recetteId;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "auteur")
    private String auteur;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "lien_image")
    private String lien_image;

    @Column(name = "lien_video")
    private String lien_video;

    @Column(name = "temps_preparation")
    private String temps_preparation;

    @Column(name = "temps_cuisson")
    private String temps_cuisson;

    @Column(name = "temps_total")
    private String temps_total;

    @Column(name = "note")
    private String note;

    @Column(name = "nb_personnes")
    private float nb_personnes;

    @Column(name = "difficulte")
    private String difficulte;

//    @OneToMany(mappedBy="recette", targetEntity = Ingredient.class, cascade = CascadeType.ALL) //
    @OneToMany(mappedBy="recette", targetEntity = Ingredient.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> liste_ingredients = new ArrayList<Ingredient>();

    @ElementCollection
    @CollectionTable(name = "etape", joinColumns = @JoinColumn(name = "recette_id"))
    @Column(name="etape")
    private List<String> liste_etapes = new ArrayList<String>();

    public String toString() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
