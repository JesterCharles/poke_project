package com.revature.pokedex.trainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Authable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.revature.pokedex.util.interfaces.Authable.checkAuth;

@RestController
@CrossOrigin //Resource Sharing, by default it allows all "*"
public class TrainerServlet implements Authable {

    private final TrainerServices trainerServices;

    @Autowired
    public TrainerServlet(TrainerServices trainerServices) {
        this.trainerServices = trainerServices;
    }

    // TODO: Implement ME

    // Create
    @PostMapping("/register")
    public ResponseEntity<Trainer> saveTrainer(@RequestBody Trainer trainer){
        Trainer newTrainer = trainerServices.create(trainer);
        return new ResponseEntity<>(newTrainer, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/trainer-findall")
    public List<Trainer> getAllTrainers(){
        return trainerServices.readAll();
    }

    @GetMapping("/trainers")
    public ResponseEntity<List> findAllTrainers(){
        // ResponseEntity takes an Object for the ResponseBody and an HTTP Status Code
        return new ResponseEntity<>(trainerServices.readAll(), HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/trainerEx")
    public void trainerEx(){
        throw new AuthenticationException("Oh no trainer not auth");
    }

    @GetMapping("/trainer/{email}")
    public ResponseEntity<Trainer> findTrainerById(@PathVariable String email){
        Trainer foundTrainer = trainerServices.readById(email);
        return new ResponseEntity<>(foundTrainer, HttpStatus.OK);
    }

    @GetMapping("/trainer")
    public Trainer findTrainerByIdQueryParam(@RequestParam String email){
        Trainer foundTrainer = trainerServices.readById(email);
        return foundTrainer;
    }

    @GetMapping("/data")
    public int showDataTypeInPath(@RequestParam int x){
        return x;
    }


    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<String> handleException(Exception e){
        String message = "Caught the invalid request with : " + e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // only use with void, if you plan to return some JSON body you have to use ResponseEntity<>
    public void handleException2(Exception e){
        String message = "Caught the invalid request2 with : " + e.getMessage();
        System.out.println(message);
    }
}
