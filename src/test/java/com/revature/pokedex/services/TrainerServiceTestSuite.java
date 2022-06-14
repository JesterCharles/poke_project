package com.revature.pokedex.services;

import com.revature.pokedex.trainer.TrainerDao;
import com.revature.pokedex.trainer.TrainerServices;
import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.trainer.Trainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TrainerServiceTestSuite {

    TrainerServices sut;
    TrainerDao mockTrainerDao;

    @BeforeEach
    public void testPrep(){
        // in order to specify and mock a dao
        mockTrainerDao = mock(TrainerDao.class);
        sut = new TrainerServices(mockTrainerDao);
    }

    @Test
    public void test_validateInput_givenValidTrainer_returnTrue(){

        // Arrange
        Trainer trainer = new Trainer("valid", "valid", "valid","valid","valid");

        // Act
        boolean actualResult = sut.validateInput(trainer);

        // Assert
        Assertions.assertTrue(actualResult);

    }

    @Test
    public void test_create_givenValidUser_returnsTrainer(){
        // Arrange
        Trainer trainer = new Trainer("pie", "pie", "pie","pie","pie");
        // THe below code ensures that the services can continue execution and get expected results from the dao without any issues
        when(mockTrainerDao.save(trainer)).thenReturn(trainer);

        // Act
        Trainer actualTrainer = sut.create(trainer);

        // Assert
        Assertions.assertEquals("pie", actualTrainer.getFname());
        Assertions.assertEquals("pie", actualTrainer.getLname());
        Assertions.assertEquals("pie", actualTrainer.getPassword());
        Assertions.assertEquals("pie", actualTrainer.getEmail());
        Assertions.assertEquals("pie", actualTrainer.getDob());
        // Mockito is verifying that the creation method was execute only once!
        verify(mockTrainerDao, times(1)).save(trainer);
    }
    @Test
    public void test_create_givenInvalidUser_throwsInvalidRequestException(){
        // Arrange
        Trainer trainer = new Trainer("pie", "", "pie","pie","pie");
        when(mockTrainerDao.save(trainer)).thenReturn(trainer);


        // Assert
        Assertions.assertThrows(InvalidRequestException.class, () -> { sut.create(trainer); });
        verify(mockTrainerDao, times(0)).save(trainer);
    }

    @Test
    public void test_create_givenRepeatedUserInformation_throwsInvalidRequestException(){
        Trainer trainer = new Trainer("pie", "", "pie","pie","pie");
        when(mockTrainerDao.existsById(trainer.getEmail())).thenReturn(true);


        // Assert
        Assertions.assertThrows(InvalidRequestException.class, () -> { sut.create(trainer);});
        verify(mockTrainerDao, times(0)).save(trainer);
    }

    @Test
    public void test_authenticateTrainer_givenInvalidInformation_throwsAuthenticationException(){
        Trainer trainer = new Trainer("pie", "", "pie","pie","pie");
        when(mockTrainerDao.authenticateTrainer(trainer.getEmail(), trainer.getPassword())).thenReturn(null);


        Assertions.assertThrows(AuthenticationException.class, () -> { sut.authenticateTrainer(trainer.getEmail(), trainer.getPassword());});
        verify(mockTrainerDao, times(1)).authenticateTrainer(trainer.getEmail(), trainer.getPassword());
    }

}
