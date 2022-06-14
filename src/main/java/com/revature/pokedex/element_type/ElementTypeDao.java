package com.revature.pokedex.element_type;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementTypeDao extends CrudRepository<ElementType, Integer> {


}
