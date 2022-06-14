package com.revature.pokedex.ability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@RestController
@CrossOrigin // CORS, this handles all cors This handles EVERYTHINg ("*")
public class AbilityServlet {

    private final AbilityServices abilityServices;

    @Autowired
    public AbilityServlet(AbilityServices abilityServices) {
        this.abilityServices = abilityServices;
    }

    // TODO: IMPLEMENT ME
}
