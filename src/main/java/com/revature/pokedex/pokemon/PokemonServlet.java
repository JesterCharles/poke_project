package com.revature.pokedex.pokemon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.ability.Ability;
import com.revature.pokedex.ability.AbilityServices;
import com.revature.pokedex.element_type.ElementType;
import com.revature.pokedex.element_type.ElementTypeServices;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Authable;
import com.revature.pokedex.util.web.dto.PokemonInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.revature.pokedex.util.interfaces.Authable.checkAuth;

@RestController
@CrossOrigin
public class PokemonServlet implements Authable {

    private final PokemonServices pokemonServices;
    private final ElementTypeServices elementTypeServices;
    private final AbilityServices abilityServices;

    @Autowired
    public PokemonServlet(PokemonServices pokemonServices, ElementTypeServices elementTypeServices, AbilityServices abilityServices) {
        this.pokemonServices = pokemonServices;
        this.elementTypeServices = elementTypeServices;
        this.abilityServices = abilityServices;
    }

    // TODO: Implement me

    @GetMapping("/pokemonEx")
    public String pokemonException(){
        throw new InvalidRequestException("You don't want to request any pokemon");
    }

}
