package com.revature.pokedex.trainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.exceptions.ResourcePersistanceException;
import com.revature.pokedex.util.interfaces.Authable;
import com.revature.pokedex.util.web.SecureEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.revature.pokedex.util.interfaces.Authable.checkAuth;

@RestController // @Controller
@CrossOrigin //Resource Sharing, by default it allows all "*"
public class TrainerServlet {

    private final TrainerServices trainerServices;

    @Autowired
    public TrainerServlet(TrainerServices trainerServices) {
        this.trainerServices = trainerServices;
    }

    // TODO: Implement ME

    // Create
    @PostMapping("/register")

    public ResponseEntity<Trainer> saveTrainer(@RequestBody @Valid Trainer trainer){
        Trainer newTrainer = trainerServices.create(trainer);
        return new ResponseEntity<>(newTrainer, HttpStatus.CREATED);
    }

    // Read
    @GetMapping("/trainer-findall")
    public List<Trainer> getAllTrainers(){
        return trainerServices.readAll();
    }

    @GetMapping("/trainers")
    @SecureEndpoint(allowedUsers = {"by@mail.com", "abczyx123@mail.com"}, isLoggedIn = true)
    public ResponseEntity<List> findAllTrainers(){
        // ResponseEntity takes an Object for the ResponseBody and an HTTP Status Code
        return new ResponseEntity<>(trainerServices.readAll(), HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/trainerEx")
    public void trainerEx(){
        throw new AuthenticationException("Oh no trainer not auth");
    }

    @GetMapping("/trainer/{email}")
    @SecureEndpoint(isLoggedIn = true)
    public ResponseEntity<Trainer> findTrainerById(@PathVariable String email){
        Trainer foundTrainer = trainerServices.readById(email);
        return new ResponseEntity<>(foundTrainer, HttpStatus.OK);
    }

    @GetMapping("/trainer")
    public Trainer findTrainerByIdQueryParam(@RequestParam String email){ // @RequestParam is those Query Parameters, .com/trainer?email=cj@mail.com
        Trainer foundTrainer = trainerServices.readById(email);
        return foundTrainer;
    }

    @GetMapping("/data")
    public int showDataTypeInPath(@RequestParam int x){
        return x;
    } // Spring will automatically convert the type based on the parameter

    @GetMapping("/persEx")
    public void throwPersEx(){
        throw new ResourcePersistanceException("How does the handler know what message is being sent here???");
    }

    @SecureEndpoint(allowedUsers = {"by@mail.com", "abczyx123@mail.com"}, isLoggedIn = true)
    @GetMapping("/secEnd")
    public String secureEndpoint(){
        return "Hey look at me from the secured endpoint";
    }



}
