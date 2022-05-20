package com.revature.pokedex.element_type;

import com.revature.pokedex.pokemon.Pokemon;
import jakarta.persistence.*;

import java.util.Objects;

public class ElementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    private String type;

    @OneToOne(mappedBy = "pokemon_name", cascade = CascadeType.ALL)
    private Pokemon pokemon;

    public ElementType() {
    }

    public ElementType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
