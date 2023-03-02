package com.revature.pokemondemo.service;

import java.util.Optional;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.repository.PokemonRepository;

import org.springframework.stereotype.Service;

@Service
public class PokemonService{
    private PokemonRepository pokeRepo;

    public PokemonService(PokemonRepository pokeRepo) {
        this.pokeRepo = pokeRepo;
    }

    public Pokemon getById(int Id) {
        //Optional class gives a possiblity of null or an object
        Optional<Pokemon> optionalPokemon = this.pokeRepo.findById(Id);

        if (!optionalPokemon.isPresent()) 
        {
            //We try our best to avoid returning a null value since that is very error prone
            //Instead we return something meaningful that would tell the user what actually happened
            return new Pokemon("Pokemon was not found");
        }
        else
        {
            return optionalPokemon.get();
        }
    }

    public Pokemon addResource(Pokemon resource) {
        if (resource == null) 
            return new Pokemon("Pokemon added was null");

        return this.pokeRepo.save(resource);
    }
}
