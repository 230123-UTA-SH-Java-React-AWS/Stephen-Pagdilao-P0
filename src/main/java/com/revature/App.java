package com.revature;

import java.util.ArrayList;

import com.revature.model.Pokemon;
import com.revature.repository.PokemonRepository;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");

        PokemonRepository pokeRepo = new PokemonRepository();
        Pokemon pokemon = new Pokemon();

        pokemon.setName("Pikachu");
        pokemon.setLevel(10);
        pokemon.setDamage(0);
        pokemon.setAbilities(new ArrayList<>());

        pokeRepo.Save(pokemon);
    }
}
