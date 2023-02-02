package com.revature.repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Ability;
import com.revature.model.Pokemon;
import com.revature.utils.ConnectionUtil;

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
        //THIS IS THE OLD WAY ON SAVING TO JSON
        //Actual implementation here
        // ObjectMapper mapper = new ObjectMapper();
        // String jsonObject = "";

        // try {
            
        //     //Converted the pokemon obj into json
        //    jsonObject = mapper.writeValueAsString(pokemon);

        //    //Save the json into a file
        //    //File constructor needs a string that holds the path of where you want to save the file
        //    File pokeFile = new File("./src/main/java/com/revature/repository/pokemon.json");
        //    pokeFile.createNewFile();

        //    //Writing the file
        //     FileWriter writer = new FileWriter("./src/main/java/com/revature/repository/pokemon.json");
        //     writer.write(jsonObject); //Writes the string into the file
        //     writer.close(); //Closes the necessary resources associated with a filewriter object

        // } catch (JsonGenerationException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (JsonMappingException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        

        //NEW WAY TO SAVE TO DATABASE INSTEAD 
        String sql = "insert into pokemon (pokename, pokelevel, health, damage, speed) values (?, ?, ?, ?, ?)";
        //JDBC API
        try (Connection con = ConnectionUtil.getConnection()) {

            PreparedStatement prstmt = con.prepareStatement(sql);

            //We are replacing the '?' into actual values from the pokemon we received
            //Sadly, it uses one-based indexing so 1 is the very first parameter
            prstmt.setString(1, pokemon.getName());
            prstmt.setInt(2, pokemon.getLevel());
            prstmt.setInt(3, pokemon.getHealth());
            prstmt.setInt(4, pokemon.getDamage());
            prstmt.setInt(5, pokemon.getSpeed());

            //execute() method does not expect to return anything from the statement
            //executeQuery() method does expect something to result after executing the statement
            prstmt.execute();
            

        } 
        catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    }

    public List<Pokemon> getAllPokemon() {
        String sql = "select * from pokemon";
        List<Pokemon> listOfPokemon = new ArrayList<Pokemon>();

        try (Connection con = ConnectionUtil.getConnection()) {

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            //Mapping information from a table to a DS instead
            while (rs.next()) {
                Pokemon newPokemon = new Pokemon();

                newPokemon.setPokeId(rs.getInt(6));
                newPokemon.setName(rs.getString(1));
                newPokemon.setLevel(rs.getInt(2));
                newPokemon.setHealth(rs.getInt(3));
                newPokemon.setDamage(rs.getInt(4));
                newPokemon.setSpeed(rs.getInt(5));
                newPokemon.setAbilities(getAbilityByPokeId(newPokemon.getPokeId()));

                listOfPokemon.add(newPokemon);
            }

        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        return listOfPokemon;
    }

    private List<Ability> getAbilityByPokeId(int pokeid)
    {
        //Implementation details
        return new ArrayList<>();
    }

}
