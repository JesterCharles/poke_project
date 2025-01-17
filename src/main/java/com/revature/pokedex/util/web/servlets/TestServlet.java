package com.revature.pokedex.util.web.servlets;

import com.revature.pokedex.pokemon.Pokemon;
import com.revature.pokedex.trainer.Trainer;
import com.revature.pokedex.util.exceptions.AuthenticationException;
import com.revature.pokedex.util.exceptions.InvalidRequestException;
import com.revature.pokedex.util.web.dto.PokemonInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/test")
public class TestServlet {

    @GetMapping("/1")
    public @ResponseBody String test(){
        return "Welcome to the wonderful world of Spring";
    }

    @GetMapping("/2")
    public @ResponseBody String test2(){
        return "Hey, this is another get method. nice right.";
    }

    @PostMapping("/post-test")
    public @ResponseBody Trainer postTest(@RequestBody Trainer trainer){
        return trainer;
    }

    @PostMapping("/post-pokemon")
    public @ResponseBody PokemonInitializer postTestPokemon(@RequestBody PokemonInitializer pokemon){
        return pokemon;
    }

    @GetMapping("/testException")
    public void testException(){
        throw new InvalidRequestException("Boooo don't hit this endpoint");
    }

    @GetMapping("/testException1")
    public void testException1(){
        throw new InvalidRequestException("Boooo don't hit this endpoint");
    }
    @GetMapping("/testException2")
    public void testException2(){
        throw new InvalidRequestException("Boooo don't hit this endpoint");
    }
    @GetMapping("/testException3")
    public void testException3(){
        throw new InvalidRequestException("Boooo don't hit this endpoint");
    }

    @GetMapping("/testException4")
    public void testException4(){
        throw new AuthenticationException("Boooo don't hit this unauthorized endpoint");
    }


}
