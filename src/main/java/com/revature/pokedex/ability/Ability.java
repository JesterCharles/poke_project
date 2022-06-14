package com.revature.pokedex.ability;

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
@Table(name = "ability")
public class Ability {
    @Id
    @Column(name = "ability_name")
    private String abilityName;
    @Column(name = "atk_multiplier")
    private int atkMultiplier;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dmg_type", referencedColumnName = "id")
    private ElementType elementType;

}
