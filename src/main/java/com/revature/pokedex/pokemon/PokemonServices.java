package com.revature.pokedex.pokemon;

import com.revature.pokedex.ability.AbilityDao;
import com.revature.pokedex.element_type.ElementTypeDao;
import com.revature.pokedex.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class PokemonServices implements Serviceable<Pokemon> {
    private final PokemonDao pokemonDao;
    private final ElementTypeDao elementTypeDao;
    private final AbilityDao abilityDao;

    @Autowired
    public PokemonServices(PokemonDao pokemonDao, ElementTypeDao elementTypeDao, AbilityDao abilityDao){
        this.pokemonDao = pokemonDao;
        this.elementTypeDao = elementTypeDao;
        this.abilityDao = abilityDao;

    }

    @Override
    public Pokemon create(Pokemon newPokemon) {
        return pokemonDao.save(newPokemon);
    }

    @Override
    public List<Pokemon> readAll() {
            return (List<Pokemon>) pokemonDao.findAll();
    }

    @Override
    public Pokemon readById(String pokemonName) {
        return pokemonDao.findById(pokemonName).get();
    }

    @Override
    public Pokemon update(Pokemon updatedPokemon) {
        pokemonDao.save(updatedPokemon);
        return updatedPokemon;
    }

    @Override
    public boolean delete(String name) {
        try {
            pokemonDao.deleteById(name);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean validateInput(Pokemon object) {
        return false;
    }
}
