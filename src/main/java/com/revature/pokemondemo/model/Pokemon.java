package com.revature.pokemondemo.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.Data;

@Entity
public @Data class Pokemon implements Validator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(length = 50)
    private String name;
    private int level;
    private int damage;
    private int health;

    @ManyToOne()
    @JsonBackReference //Avoid Jackson repeated recursion
    private Trainer trainer;

    public Pokemon() { }

    public Pokemon(String name) {
        this.name = name;
    }
    
    //Checks if what is being compared is a class form this class
    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return Pokemon.class.equals(clazz);
    }

    //One method to have all the validation
    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub
        ValidationUtils.rejectIfEmpty(errors, name, "name field is empty");
        Pokemon p = (Pokemon) target;

        Field[] fields = Pokemon.class.getDeclaredFields();
        
        for (Field field : fields) {
            try {
                if (field.getType().toString().equals("int") && field.getInt(p) < 1) {
                    errors.rejectValue(field.getName(), field.getName() + " cannot be less than 1");
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
