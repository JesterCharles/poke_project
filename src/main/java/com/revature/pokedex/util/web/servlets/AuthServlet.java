package com.revature.pokedex.util.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.trainer.Trainer;
import com.revature.pokedex.trainer.TrainerServices;
import com.revature.pokedex.util.web.dto.LoginCreds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// @WebServlet("/auth") // this requires a default No-Args constructor, but we make our own constructor in line 24
@RestController
@RequestMapping("/auth")
public class AuthServlet {

    private final TrainerServices trainerServices;

    @Autowired
    public AuthServlet(TrainerServices trainerServices){
        this.trainerServices = trainerServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authorizeTrainer(@RequestBody LoginCreds loginCreds, HttpSession httpSession){
        Trainer authTrainer = trainerServices.authenticateTrainer(loginCreds.getEmail(), loginCreds.getPassword());
        httpSession.setAttribute("authTrainer", authTrainer);
    }

    @DeleteMapping
    public void logout(HttpSession session){
        session.invalidate();
    }

}
