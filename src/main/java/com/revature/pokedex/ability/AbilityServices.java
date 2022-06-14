package com.revature.pokedex.ability;

import com.revature.pokedex.element_type.ElementTypeDao;
import com.revature.pokedex.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional // Once again best practice
public class AbilityServices implements Serviceable<Ability> {


    private final ElementTypeDao elementTypeDao;
    private final AbilityDao abilityDao;

    // DI - Dependency Injection
    @Autowired
    public AbilityServices(ElementTypeDao elementTypeDao, AbilityDao abilityDao) {
        this.elementTypeDao = elementTypeDao;
        this.abilityDao = abilityDao;
    }

    @Override
    public Ability create(Ability newAbility) {
        return abilityDao.save(newAbility);
    }

    @Override
    public List<Ability> readAll() {
            return (List<Ability>) abilityDao.findAll();

    }

    @Override
    public Ability readById(String abilityName) {
        return abilityDao.findById(abilityName).get();
    }

    @Override
    public Ability update(Ability updatedAbility) {
        return abilityDao.save(updatedAbility);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(Ability object) {
        return false;
    }
}
