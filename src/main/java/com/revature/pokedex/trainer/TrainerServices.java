package com.revature.pokedex.trainer;

import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Serviceable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional // This is handled each one of these method calls as an individual transaction
public class TrainerServices implements Serviceable<Trainer> {

    private TrainerDao trainerDao;

    @Autowired // not really necessary, but good practice
    public TrainerServices(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    @Override
    public List<Trainer> readAll(){
        // TODO: What trainerDao intellisense telling me?
        // Casting the findAll Iterable<Trainer> to a List<Trainer>
        List<Trainer> trainers = (List<Trainer>) trainerDao.findAll();
        return trainers;
    }

    @Override
    public Trainer readById(String id) throws ResourcePersistanceException{

        // Add .get() after findById as it is an Optional and not just a Trainer class that is returned byt he CrudRepository
        Trainer trainer = trainerDao.findById(id).get();
        return trainer;
    }

    @Override
    public Trainer update(Trainer updatedTrainer) {
        trainerDao.save(updatedTrainer);

        return updatedTrainer;
    }

    @Override
    public boolean delete(String email) {
        trainerDao.deleteById(email);
        return true;
    }

    public boolean validateEmailNotUsed(String email){
        return trainerDao.existsById(email);
    }
    
    public Trainer create(Trainer newTrainer){
        if(!validateInput(newTrainer)){ // checking if false
            // TODO: throw - what's this keyword?
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if(validateEmailNotUsed(newTrainer.getEmail())){
            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
        }

        Trainer persistedTrainer = trainerDao.save(newTrainer);

        if(persistedTrainer == null){
            throw new ResourcePersistanceException("Trainer was not persisted to the database upon registration");
        }
        return persistedTrainer;
    }

    @Override
    public boolean validateInput(Trainer newTrainer) {
        if(newTrainer == null) return false;
        if(newTrainer.getFname() == null || newTrainer.getFname().trim().equals("")) return false;
        if(newTrainer.getLname() == null || newTrainer.getLname().trim().equals("")) return false;
        if(newTrainer.getEmail() == null || newTrainer.getEmail().trim().equals("")) return false;
        if(newTrainer.getPassword() == null || newTrainer.getPassword().trim().equals("")) return false;
        return newTrainer.getDob() != null || !newTrainer.getDob().trim().equals("");
    }

    public Trainer authenticateTrainer(String email, String password){

        if(password == null || password.trim().equals("") || email == null || email.trim().equals("")) {
            throw new InvalidRequestException("Either email or password is an invalid entry. Please try logging in again");
        }

        Optional<Trainer> authenticatedTrainer = trainerDao.authenticateTrainer(email, password);

        if (!authenticatedTrainer.isPresent()){
            throw new AuthenticationException("Unauthenticated user, information provided was not consistent with our database.");
        }

        return authenticatedTrainer.get();

    }
}
