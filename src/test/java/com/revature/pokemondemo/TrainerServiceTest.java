package com.revature.pokemondemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import com.revature.pokemondemo.model.Pokemon;
import com.revature.pokemondemo.model.Trainer;
import com.revature.pokemondemo.repository.PokemonRepository;
import com.revature.pokemondemo.repository.TrainerRepository;
import com.revature.pokemondemo.service.TrainerService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;


@SpringBootTest
public class TrainerServiceTest {
    
    @Autowired
    private TrainerService trainerService;

    //MockBean mocks the class
    @MockBean
    private TrainerRepository trainRepo;

    @MockBean
    private PokemonRepository pokeRepo;

    private Trainer trainerDummy;

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
    }

    @Test
    public void Find_Trainer_Should_Work_With_Existing_Trainer()
    {
        //Arrange
        Mockito.when(trainRepo.findByUsernameAndPassword(trainerDummy.getUsername(), trainerDummy.getPassword()))
            .thenReturn(Optional.ofNullable(trainerDummy));

        //Act
        Trainer actualTrainer = trainerService.findTrainer(trainerDummy);

        //Assert
        Assertions.assertNotNull(actualTrainer);
        Assertions.assertEquals(trainerDummy.getName(), actualTrainer.getName());
    }

    @Test
    public void Find_Trainer_Should_Fail_With_No_Trainer_Found()
    {
        //Arrange
        Mockito.when(trainRepo.findByUsernameAndPassword(trainerDummy.getUsername(), trainerDummy.getPassword()))
            .thenReturn(Optional.empty());
        
        //Act
        Trainer actualTrainer = trainerService.findTrainer(trainerDummy);

        //Assert
        Assertions.assertNotNull(actualTrainer);
        Assertions.assertEquals(actualTrainer.getName(), "Trainer was not found");
    }

    @Test
    public void Add_Resource_Should_Work_With_Not_Null_Resource()
    {
        //Arrange
        Mockito.when(trainRepo.save(trainerDummy))
            .thenReturn(trainerDummy);

        //Act
        Trainer actualTrainer = trainerService.addResource(trainerDummy);

        //Assert
        Assertions.assertNotNull(actualTrainer);
        Assertions.assertEquals(actualTrainer.getName(), trainerDummy.getName());
    }

    @Test
    public void Add_Resource_Should_Fail_With_Null_Resource()
    {
        //Arrange
        
        //Act
        Trainer actualTrainer = trainerService.addResource(null);

        //Assert
        Assertions.assertNotNull(actualTrainer);
        Assertions.assertEquals(actualTrainer.getName(), "Trainer added was null");
    }

    @Test
    public void Add_Pokemon_Should_Work_With_Existing_User()
    {
        //Arrange
        String name = "Pikachu";
        int id = 1, level = 1, health = 1;
        Pokemon expectedPika = new Pokemon();
        expectedPika.setName(name);
        expectedPika.setLevel(level);
        expectedPika.setId(id);
        expectedPika.setHealth(health);

        Mockito.when(trainRepo.findById(trainerDummy.getId()))
            .thenReturn(Optional.ofNullable(trainerDummy));
        
        Mockito.when(pokeRepo.save(expectedPika))
            .thenReturn(expectedPika);
        
        //Act
        Trainer actualTrainer = trainerService.addPokemon(trainerDummy.getId(), expectedPika);

        //Assert
        Assertions.assertNotNull(actualTrainer);
        Assertions.assertEquals(actualTrainer.getPokemons().get(0), expectedPika);
    }
}
