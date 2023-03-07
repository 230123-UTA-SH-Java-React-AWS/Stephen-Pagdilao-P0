package com.revature.pokemondemo;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.model.Trainer;
import com.revature.pokemondemo.service.TrainerService;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainerService trainerServ;

    private Trainer trainerDummy;
    private Pokemon pokemonDummy;

    //Runs before each unit test
    //Cleans our trainerDummy object in the event it was touched by another unit test
    @BeforeEach
    public void initialSetup()
    {
        String name = "Stephen", username = "stephen", password = "stephen123";
        int id = 1;
        trainerDummy = new Trainer();
        trainerDummy.setName(name);
        trainerDummy.setUsername(username);
        trainerDummy.setPassword(password);
        trainerDummy.setId(id);
        
        String pokename = "Pikachu";
        int pokeid = 1, health = 1, damage = 1, level = 1;
        pokemonDummy = new Pokemon();
        pokemonDummy.setDamage(damage);
        pokemonDummy.setId(pokeid);
        pokemonDummy.setHealth(health);
        pokemonDummy.setLevel(level);
        pokemonDummy.setName(pokename);

        trainerDummy.getPokemons().add(pokemonDummy);
    }

    @Test
    public void Add_Trainer_Should_Add_A_Trainer() throws Exception
    {
        //Arrange
        Mockito.when(trainerServ.addResource(any(Trainer.class)))
            .thenReturn(trainerDummy);

        mockMvc.perform(post("/trainer")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Stephen\",\"username\":\"stephen123\",\"password\":\"password123\"}"))
            .andExpect(status().isOk()) //checks status code
            .andExpect(jsonPath("$.name", is("Stephen"))) //checks content of the response body, granted I only really checked just a couple
            .andExpect(jsonPath("$.id", is(1))); 
    }

    @Test
    public void Add_Trainer_Should_Fail_With_Missing_Params() throws Exception
    {
        Mockito.when(trainerServ.addResource(any(Trainer.class)))
            .thenReturn(trainerDummy);

            mockMvc.perform(post("/trainer")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")) //Empty json object given
            .andExpect(status().isBadRequest()); //checks status code
    }

    @Test
    public void Add_Pokemon_To_Trainer_Should_Work_With_Existing_User_And_Valid_Pokemon() throws Exception
    {
        Mockito.when(trainerServ.addPokemon(anyInt(), any(Pokemon.class)))
            .thenReturn(trainerDummy);

            mockMvc.perform(post("/trainer/1/addPokemon")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Pikachu\",\"level\":10,\"damage\":20,\"health\":30}"))
            .andExpect(status().isOk()) //checks status code
            .andExpect(jsonPath("$.pokemons[0].name", is("Pikachu"))) //checks content of the response body, granted I only really checked just a couple
            .andExpect(jsonPath("$.pokemons[0].id", is(1))); 
    }
}
