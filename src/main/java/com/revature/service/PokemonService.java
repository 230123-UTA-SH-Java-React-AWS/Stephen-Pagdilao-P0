package com.revature.service;

import com.revature.model.Pokemon;
import com.revature.repository.PokemonRepository;

/*
    The service layer is responsible for hold behavior driven classes
    It might have further validation or information process within the service
*/
public class PokemonService {

    public void battle()
    {

    }
    public void death()
    {

    }

    public void saveToPokeBox(Pokemon poke)
    {
        PokemonRepository repo = new PokemonRepository();

        repo.Save(poke);
    }

    public void findPokemon()
    {

    }
}
