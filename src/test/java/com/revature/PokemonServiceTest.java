package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.revature.model.Pokemon;
import com.revature.repository.PokemonRepository;
import com.revature.service.PokemonService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/*
    Mocking is when you replace a real class into something that isn't real and usually returns a hardcoded value

    Why do this? This guarantees that the class we depend on will always work
*/

public class PokemonServiceTest {
    
    @Test
    public void Get_All_Pokemon_Should_Give_All_Pokemon()
    {
        //Arrange
        PokemonRepository mockPokeRepo = Mockito.mock(PokemonRepository.class);
        PokemonService pokeServ = new PokemonService(mockPokeRepo);

        //Telling Mockito to guarantee that if a specific method was called in our mock object, go ahead return hardcoded value
        List<Pokemon> expectedListOfPokemon = new ArrayList<>();
        expectedListOfPokemon.add(new Pokemon());
        expectedListOfPokemon.add(new Pokemon());
        expectedListOfPokemon.add(new Pokemon());

        Mockito.when(mockPokeRepo.getAllPokemon()).thenReturn(expectedListOfPokemon);

        //Act
        String jsonListPokemon = pokeServ.getAllPokemon();

        //Assert
        assertNotEquals("null", jsonListPokemon);
    }
}
