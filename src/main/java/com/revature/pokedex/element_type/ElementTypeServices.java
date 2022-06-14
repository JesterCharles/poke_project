package com.revature.pokedex.element_type;

import com.revature.pokedex.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
@Service
@Transactional
public class ElementTypeServices implements Serviceable<ElementType> {
    private final ElementTypeDao elementTypeDao;

    @Autowired
    public ElementTypeServices(ElementTypeDao elementTypeDao) {
        this.elementTypeDao = elementTypeDao;
    }

    @Override
    public ElementType create(ElementType newElementType) {
        return elementTypeDao.save(newElementType);
    }

    @Override
    public List<ElementType> readAll() {
            return (List<ElementType>) elementTypeDao.findAll();
    }

    @Override
    public ElementType readById(String id) {
       int idInt = Integer.parseInt(id);
        return elementTypeDao.findById(idInt).get();
    }

    @Override
    public ElementType update(ElementType updatedElementType) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean validateInput(ElementType object) {
        return false;
    }
}
