package com.revature.pokemondemo.controller;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.service.PokemonService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pokemon")
public class PokemonController {
    
    private PokemonService pokeServ;

    public PokemonController(PokemonService pokemonService) {
        this.pokeServ = pokemonService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Pokemon getPokemonById(int id)
    {
        return this.pokeServ.getById(id);
    }

    @RequestMapping(path = "/someNew", method = RequestMethod.GET)
    public String giveHello()
    {
        return "Hello World";
    }
}
