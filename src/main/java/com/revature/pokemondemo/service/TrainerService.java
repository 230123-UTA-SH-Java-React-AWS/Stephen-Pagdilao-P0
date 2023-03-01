package com.revature.pokemondemo.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.model.Trainer;
import com.revature.pokemondemo.repository.PokemonRepository;
import com.revature.pokemondemo.repository.TrainerRepository;

import org.springframework.stereotype.Service;

@Service
public class TrainerService{
    private TrainerRepository trainRepo;
    private PokemonRepository pokeRepo;

    public TrainerService(TrainerRepository trainRepo, PokemonRepository pokeRepo) {
        this.trainRepo = trainRepo;
        this.pokeRepo = pokeRepo;
    }

    public Trainer getById(int Id) {
        Optional<Trainer> optionalTrainer = this.trainRepo.findById(Id);

        if (optionalTrainer.isEmpty()) 
        {
            //We try our best to avoid returning a null value since that is very error prone
            //Instead we return something meaningful that would tell the user what actually happened
            return new Trainer("Trainer is not found");
        }
        else
        {
            return optionalTrainer.get();
        }
    }

    public Trainer addResource(Trainer resource) {
        if (resource == null) 
            return new Trainer("Pokemon added was null");

        return this.trainRepo.save(resource);
    }

    @Transactional()
    public Trainer addPokemon(int userId, Pokemon pokemon)
    {
        Optional<Trainer> optionalTrainer = this.trainRepo.findById(userId);

        if (optionalTrainer.isEmpty()) {
            return new Trainer("Trainer is not found");
        }
        else
        {
            Trainer trainer = optionalTrainer.get();
            trainer.getPokemons().add(pokemon);
            pokemon.setTrainer(trainer);
            this.pokeRepo.save(pokemon);
            
            return trainer;
        }
    }
}
