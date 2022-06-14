package com.revature.pokedex.pokemon;

import com.revature.pokedex.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
@Repository
public interface PokemonDao extends CrudRepository<Pokemon,String> {

}
