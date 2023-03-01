package com.revature.pokemondemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.Data;

@Entity
public @Data class Trainer implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "trainer", orphanRemoval = true) //In the event that this trainer does get removed, it will also remove the trainer's pokemons
    @JsonManagedReference
    private List<Pokemon> pokemons = new ArrayList<>();

    public Trainer() { }

    public Trainer(String name) {
        this.name = name;   
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return Trainer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub
        ValidationUtils.rejectIfEmpty(errors, "name", "name field is empty");

        if (Id < 1) {
            errors.rejectValue("Id", "Id cannot be less than 1");
        }
    }

    
}
