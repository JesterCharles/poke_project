package com.revature.pokedex.ability;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityDao extends CrudRepository<Ability, String> {

}
