package com.revature.pokedex.pokemon;

import com.revature.pokedex.ability.Ability;
import com.revature.pokedex.element_type.ElementType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
@Data // this handles toString, hashCode, equals() and your getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Column(name = "pokemon_name")
    private String pokemonName;
    private int hp;
    private int atk;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "element_type", referencedColumnName = "id")
    private ElementType elementType;
    @ManyToOne(optional = false)
    @JoinColumn(name="ability1", referencedColumnName = "ability_name")
    private Ability ability1;
    @ManyToOne(optional = false)
    @JoinColumn(name="ability2", referencedColumnName = "ability_name")
    private Ability ability2;


}
