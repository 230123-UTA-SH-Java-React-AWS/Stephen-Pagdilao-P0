package com.revature.pokemondemo.controller;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.model.Trainer;
import com.revature.pokemondemo.service.TrainerService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trainer")
public class TrainerController {

    private TrainerService trainServ;

    public TrainerController(TrainerService trainerService) {
        this.trainServ = trainerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Trainer addTrainer(@RequestBody Trainer trainer)
    {
        return this.trainServ.addResource(trainer);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Trainer getTrainerById(int id)
    {
        return this.trainServ.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/{id}/addPokemon")
    public Trainer addPokemonToTrainer(@PathVariable("id")int userId, @RequestBody Pokemon pokemon)
    {
        return this.trainServ.addPokemon(userId, pokemon);
    }
}
