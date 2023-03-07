package com.revature.pokemondemo;

import com.revature.pokemondemo.model.Trainer;
import com.revature.pokemondemo.service.TrainerService;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
    public void Add_Trainer_Should_Add_A_Trainer() throws Exception
    {
        //Arrange
        Mockito.when(trainerServ.addResource(trainerDummy))
            .thenReturn(trainerDummy);

        MvcResult result = mockMvc.perform(post("/trainer")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Stephen\",\"username\":\"stephen123\",\"password\":\"password123\"}"))
            .andExpect(status().isOk())
            .andReturn();
            
        String json = result.getResponse().getContentAsString();
        
    }
}
