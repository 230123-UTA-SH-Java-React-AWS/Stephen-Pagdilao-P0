package com.revature.pokemondemo.controller;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.service.BaseService;
import com.revature.pokemondemo.service.PokemonService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/pokemon")
public class PokemonController {
    @Qualifier("PokemonService")
    private BaseService<Pokemon> pokeServ;

    public PokemonController(BaseService<Pokemon> pokemonService) {
        this.pokeServ = pokemonService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Pokemon getPokemonById(int id)
    {
        return this.pokeServ.getById(id);
    }
}
