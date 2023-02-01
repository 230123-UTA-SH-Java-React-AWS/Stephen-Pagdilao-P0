package com.revature.service;

import java.io.IOException;
import java.util.List;

import com.revature.model.Pokemon;
import com.revature.repository.PokemonRepository;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

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

    public void saveToPokeBox(String pokeJson)
    {
        PokemonRepository repo = new PokemonRepository();
        //Conversion from string to pokemon obj here?
        ObjectMapper mapper = new ObjectMapper();

        try {
            Pokemon newPokemon = mapper.readValue(pokeJson, Pokemon.class);
            repo.Save(newPokemon);

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Converting List into 
    public String getAllPokemon()
    {
        PokemonRepository repo = new PokemonRepository();
        List<Pokemon> listOfPoke = repo.getAllPokemon();

        ObjectMapper map = new ObjectMapper();

        String jsonString = "";

        try {
            jsonString = map.writeValueAsString(listOfPoke);

        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonString;
    }

    public void findPokemon()
    {

    }
}
