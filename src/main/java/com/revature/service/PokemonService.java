package com.revature.service;

import java.io.IOException;
import java.util.List;

import com.revature.model.Pokemon;
import com.revature.repository.AbilityRepository;
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

    private final PokemonRepository pokerepo = new PokemonRepository();
    private final ObjectMapper mapper = new ObjectMapper();

    public void battle()
    {

    }
    public void death()
    {

    }

    public void saveToPokeBox(String pokeJson)
    {
        //Conversion from string to pokemon obj here?

        try {
            Pokemon newPokemon = mapper.readValue(pokeJson, Pokemon.class);
            pokerepo.Save(newPokemon);

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
        List<Pokemon> listOfPoke = pokerepo.getAllPokemon();
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(listOfPoke);

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
