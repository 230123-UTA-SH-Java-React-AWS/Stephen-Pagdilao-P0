package com.revature.pokemondemo.repository;

import java.util.Optional;

import com.revature.pokemondemo.model.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{
    Optional<Trainer> findByUsernameAndPassword(String username, String password);
}
