package com.revature.pokedex.trainer;

import com.revature.pokedex.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.Optional;

// DAO is now an interfact and requires the extension of CrudRepository<DataTypeOfModel, IDDataType>
@Repository
public interface TrainerDao extends CrudRepository<Trainer, String> {

    @Query(value = "FROM Trainer WHERE email= :email AND password = :password")
    Optional<Trainer> authenticateTrainer(String email, String password);
}
