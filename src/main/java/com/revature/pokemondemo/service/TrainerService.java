package com.revature.pokemondemo.service;

import java.util.Optional;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.model.Trainer;
import com.revature.pokemondemo.repository.TrainerRepository;

import org.springframework.stereotype.Service;

@Service
public class TrainerService implements BaseService<Trainer>{
    private TrainerRepository trainRepo;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainRepo = trainerRepository;
    }

    @Override
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

    @Override
    public Trainer addResource(Trainer resource) {
        if (resource == null) 
            return new Trainer("Pokemon added was null");

        return this.trainRepo.save(resource);
    }
}
