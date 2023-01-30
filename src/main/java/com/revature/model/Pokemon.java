package com.revature.model;

import java.util.ArrayList;
import java.util.List;

/*
    Model layer is responsible for hold stateful objects
    objects that have information that differs from another object from the same class
*/

public class Pokemon {
    //Uniquely identifiable field that is numeric
    private int pokeId;
    
    //Fields that will be useful to uniquely identify this object
    private String name;
    private int level;
    private int health;
    private int damage;
    private int speed;

    //What data structure allows us to add multiple things
    private List<Ability> abilities;

    public Pokemon() {
        this.abilities = new ArrayList<>();
    }

    
    //right click, click source action, click generate getters and setters, select all fields, hit ok
    public int getPokeId() {
        return pokeId;
    }

    public void setPokeId(int pokeId) {
        this.pokeId = pokeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}
