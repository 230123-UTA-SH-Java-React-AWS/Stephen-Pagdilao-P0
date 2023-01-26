package com.revature.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.revature.model.Pokemon;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/*
    Repository layer is responsible for interacting with a database and sending/receiving information from the database
*/
public class PokemonRepository {
    //Create a method in PokemonRepository that allows you to store in a file locally in your computer
    public void Save(Pokemon pokemon)
    {
        //Actual implementation here
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = "";

        try {
            
            //Converted the pokemon obj into json
           jsonObject = mapper.writeValueAsString(pokemon);

           //Save the json into a file
           //File constructor needs a string that holds the path of where you want to save the file
           File pokeFile = new File("./src/main/java/com/revature/repository/pokemon.json");
           pokeFile.createNewFile();

           //Writing the file
            FileWriter writer = new FileWriter("./src/main/java/com/revature/repository/pokemon.json");
            writer.write(jsonObject); //Writes the string into the file
            writer.close(); //Closes the necessary resources associated with a filewriter object

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
        
    }
}
