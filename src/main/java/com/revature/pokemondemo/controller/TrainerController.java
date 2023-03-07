package com.revature.pokemondemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.model.Trainer;
import com.revature.pokemondemo.service.TrainerService;
import com.revature.pokemondemo.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerController {

    private TrainerService trainServ;
    private JwtTokenUtil jwtToken;

    public TrainerController(TrainerService trainerService, JwtTokenUtil jwtTokenUtil) {
        this.trainServ = trainerService;
        this.jwtToken = jwtTokenUtil;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Trainer addTrainer(@RequestBody Trainer trainer, HttpServletResponse response)
    {
        if (trainer.getName() == null ||
            trainer.getUsername() == null ||
            trainer.getPassword() == null) 
        {
            response.setStatus(400);
            Trainer failed = new Trainer();
            failed.setName("Trainer has missing required parameter(s)");
            return failed;
        }

        response.setStatus(201);
        return this.trainServ.addResource(trainer);
    }

    @RequestMapping(method = RequestMethod.POST, value="/{id}/addPokemon")
    public Trainer addPokemonToTrainer(@PathVariable("id")int userId, 
                    @RequestBody Pokemon pokemon,
                    HttpServletResponse response)
    {
        if (pokemon.getName() == null) 
        {
            response.setStatus(400);
            Trainer failed = new Trainer();
            failed.setName("Pokemon has missing required parameter(s)");
            return failed;
        }

        Trainer foundTrainer = this.trainServ.addPokemon(userId, pokemon);

        if (foundTrainer.getName().equals("Trainer was not found")) 
            response.setStatus(404);
        
        return foundTrainer;
    }
}
